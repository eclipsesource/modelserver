/*******************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
 *
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License v. 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0.
 *
 *   This Source Code may also be made available under the following Secondary
 *   Licenses when the conditions for such availability set forth in the Eclipse
 *   Public License v. 2.0 are satisfied: GNU General Public License, version 2
 *   with the GNU Classpath Exception which is available at
 *   https://www.gnu.org/software/classpath/license.html.
 *
 *   SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 *******************************************************************************/
package com.eclipsesource.modelserver.emf.common;

import com.eclipsesource.modelserver.emf.EMFJsonConverter;
import com.eclipsesource.modelserver.emf.common.codecs.Encoder;
import com.eclipsesource.modelserver.emf.common.codecs.EncodingException;
import com.eclipsesource.modelserver.emf.common.codecs.JsonCodec;
import com.eclipsesource.modelserver.jsonschema.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.json.JavalinJackson;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class ModelController implements CrudHandler {

	private static final Logger LOG = Logger.getLogger(ModelController.class.getSimpleName());

	private ModelRepository modelRepository;
	private SessionController sessionController;
	private Encoder encoder;

	@Inject public ModelController(ModelRepository modelRepository, SessionController sessionController) {
		JavalinJackson.configure(EMFJsonConverter.setupDefaultMapper());
		encoder = new Encoder();
		this.modelRepository = modelRepository;
		this.sessionController = sessionController;
	}

	@Override
	public void create(Context ctx) {
		readEObject(ctx).ifPresent(eObject -> {
			EStructuralFeature name = eObject.eClass().getEStructuralFeature("name");
			if (eObject.eGet(name) == null) {
				handleError(ctx, 400, "Create new model failed: Model identifier (name) is missing");
				return;
			}

			String modeluri = eObject.eGet(name).toString().replaceAll(" ", "");
			this.modelRepository.addModel(modeluri, eObject);
			try {
				final JsonNode encoded = encoder.encode(ctx, eObject);
				ctx.json(JsonResponse.data(encoded));
				this.sessionController.modelChanged(modeluri, ctx);
			} catch (EncodingException ex) {
				handleEncodingError(ctx, ex);
			}
		});
	}

	@Override
	public void delete(Context ctx, String modeluri) {
		if (this.modelRepository.hasModel(modeluri)) {
			this.modelRepository.removeModel(modeluri);
			ctx.json(JsonResponse.confirm("Model '" + modeluri + "' successfully deleted"));
			this.sessionController.modelDeleted(modeluri, ctx);
		} else {
			handleError(ctx, 404, "Model '" + modeluri + "' not found, cannot be deleted!");
		}
	}

	@Override
	public void getAll(Context ctx) {
		final Map<URI, EObject> allModels = this.modelRepository.getAllModels();
		try {
			Map<URI, JsonNode> encodedEntries = Maps.newLinkedHashMap();
			for (Map.Entry<URI, EObject> entry : allModels.entrySet()) {
				final JsonNode encoded = encoder.encode(ctx, entry.getValue());
				encodedEntries.put(entry.getKey(), encoded);
			}
			ctx.json(JsonResponse.data(JsonCodec.encode(encodedEntries)));
		} catch (EncodingException ex) {
			handleEncodingError(ctx, ex);
		}
	}

	@Override
	public void getOne(Context ctx, String modeluri) {
		this.modelRepository.getModel(modeluri).ifPresentOrElse(
			model -> {
				if (model == null) {
					ctx.json(JsonResponse.data(Json.text("")));
				} else {
					try {
						ctx.json(JsonResponse.data(encoder.encode(ctx, model)));
					} catch (EncodingException ex) {
						handleEncodingError(ctx, ex);
					}
				}
			},
			() -> handleError(ctx, 404, "Model '" + modeluri + "' not found!")
		);
	}

	@Override
	public void update(Context ctx, String modeluri) {
		readEObject(ctx).ifPresent(
			eObject -> {
				modelRepository.updateModel(modeluri, eObject);
				try {
					ctx.json(JsonResponse.data(encoder.encode(ctx, eObject)));
				} catch (EncodingException e) {
					handleEncodingError(ctx, e);
				}
				sessionController.modelChanged(modeluri, ctx);
			}
		);
	}

	public Handler modelUrisHandler = ctx -> {
		ctx.json(JsonResponse.data(JsonCodec.encode(this.modelRepository.getAllModelUris())));
	};

	private Optional<EObject> readEObject(Context ctx) {
		final EMFJsonConverter emfJsonConverter = new EMFJsonConverter();

		try {
			JsonNode json = JavalinJackson.getObjectMapper().readTree(ctx.body());
			if (!json.has("data")) {
				handleError(ctx, 400, "Empty JSON");
				return Optional.empty();
			}
			String jsonData = json.get("data").toString();
			if (jsonData.equals("{}")) {
				handleError(ctx, 400, "Empty JSON");
				return Optional.empty();
			}
			return emfJsonConverter.fromJson(jsonData);
		} catch (IOException e) {
			handleError(ctx, 400, "Invalid JSON");
		}
		return Optional.empty();
	}

	private void handleEncodingError(Context context, EncodingException ex) {
		handleError(context, 500, "An error occurred during data encoding", ex);
	}

	private void handleError(Context ctx, int statusCode, String errorMsg) {
		LOG.error(errorMsg);
		ctx.status(statusCode).json(JsonResponse.error(errorMsg));
	}

	private void handleError(Context ctx, int statusCode, String errorMsg, Exception e) {
		LOG.error(errorMsg, e);
		ctx.status(statusCode).json(JsonResponse.error(errorMsg));
	}
}
