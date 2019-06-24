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

import java.util.Optional;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.eclipsesource.modelserver.emf.EMFJsonConverter;
import com.google.inject.Inject;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.json.JavalinJackson;

public class ModelController implements CrudHandler {

	private static final Logger LOG = Logger.getLogger(ModelController.class);

	@Inject
	private ModelRepository modelRepository;

	public ModelController() {
		JavalinJackson.configure(EMFJsonConverter.setupDefaultMapper());
	}

	@Override
	public void create(Context ctx) {
		try {
			EObject model = ctx.bodyAsClass(EObject.class);
			
			EStructuralFeature name = model.eClass().getEStructuralFeature("name");
			String modelUri = model.eGet(name).toString().replaceAll(" ", "");
			
			this.modelRepository.addModel(modelUri, model);
		} catch (BadRequestResponse r) {
			handleError(ctx, 400, r.getMessage());
		} catch (NullPointerException e) {
			handleError(ctx, 400, "Create new model failed: Model identifier (name) is missing");
		}
	}

	@Override
	public void delete(Context ctx, String modeluri) {
		this.modelRepository.removeModel(modeluri);
	}

	@Override
	public void getAll(Context ctx) {
		ctx.json(this.modelRepository.getAllModels());
	}

	@Override
	public void getOne(Context ctx, String modeluri) {
		Optional<Context> opt = this.modelRepository.getModel(modeluri).map(ctx::json);
		if (!opt.isPresent())
			handleError(ctx, 404, "Model not found!");
	}

	@Override
	public void update(Context ctx, String modeluri) {
		EObject model = ctx.bodyAsClass(EObject.class);
		this.modelRepository.updateModel(modeluri, model);
	}

	public Handler getModelUris = ctx -> {
		ctx.json(this.modelRepository.getAllModelUris());
	};

	private void handleError(Context ctx, int statusCode, String errorMsg) {
		LOG.error(errorMsg);
		ctx.status(statusCode).result(errorMsg);
	}
}
