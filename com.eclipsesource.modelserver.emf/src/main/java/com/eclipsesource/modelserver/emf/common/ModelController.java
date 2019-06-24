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

import java.io.IOException;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.eclipsesource.modelserver.emf.EMFJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.json.JavalinJackson;

public class ModelController implements CrudHandler {

	private static final Logger LOG = Logger.getLogger(ModelController.class.getSimpleName());

	@Inject
	private ModelRepository modelRepository;
	@Inject
	private SessionController sessionController;

	public ModelController() {
		JavalinJackson.configure(EMFJsonConverter.setupDefaultMapper());
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

			ctx.json(JsonResponse.data(eObject));
			
			this.sessionController.modelChanged(modeluri);
		});
	}

	@Override
	public void delete(Context ctx, String modeluri) {
		if (this.modelRepository.hasModel(modeluri)) {
			this.modelRepository.removeModel(modeluri);
			ctx.json(JsonResponse.confirm("Model '" + modeluri + "' successfully deleted"));
			this.sessionController.modelDeleted(modeluri);
		} else {
			handleError(ctx, 404, "Model '" + modeluri + "' not found, cannot be deleted!");
		}
	}

	@Override
	public void getAll(Context ctx) {
		ctx.json(JsonResponse.data(this.modelRepository.getAllModels()));
	}

	@Override
	public void getOne(Context ctx, String modeluri) {
		this.modelRepository.getModel(modeluri).ifPresentOrElse(
				model -> ctx.json(JsonResponse.data(model)),
				() -> handleError(ctx, 404, "Model '" + modeluri + "' not found!")
		);
	}

	@Override
	public void update(Context ctx, String modeluri) {
		readEObject(ctx).ifPresent(
				eObject -> {
					modelRepository.updateModel(modeluri, eObject);
					ctx.json(JsonResponse.data(eObject));
					sessionController.modelChanged(modeluri);
				}
		);
	}

	public Handler modelUrisHandler = ctx -> {
		ctx.json(JsonResponse.data(this.modelRepository.getAllModelUris()));
	};

	private Optional<EObject> readEObject(Context ctx) {
		final EMFJsonConverter emfJsonConverter = new EMFJsonConverter();
		
		try {
			JsonNode json = JavalinJackson.getObjectMapper().readTree(ctx.body());
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

	private void handleError(Context ctx, int statusCode, String errorMsg) {
		LOG.error(errorMsg);
		ctx.status(statusCode).json(JsonResponse.error(errorMsg));
	}
}
