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

import com.eclipsesource.modelserver.common.ModelServerPaths;
import com.eclipsesource.modelserver.common.Routing;
import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;
import com.google.inject.Inject;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ModelServerRouting extends Routing {

	private static final Logger LOG = Logger.getLogger(ModelServerRouting.class.getSimpleName());

	private Javalin javalin;
	private ServerConfiguration serverConfiguration;

	@Inject
	public ModelServerRouting(Javalin javalin, ServerConfiguration serverConfiguration) {
		this.javalin = javalin;
		this.serverConfiguration = serverConfiguration;
	}

	@Override
	public void bindRoutes() {
		javalin.routes(() -> {
			path("api/v1", () -> {
				// CREATE
				post(ModelServerPaths.MODEL_BASE_PATH, ctx -> {
					getQueryParam(ctx.queryParamMap(), "modeluri")
						.map(this::adaptModelUri)
						.ifPresentOrElse(
							param -> getController(ModelController.class).create(ctx, param),
							() -> handleError(ctx, 400, "Missing parameter 'modeluri'!")
					);
				});
				
				// GET ONE/GET ALL
				get(ModelServerPaths.MODEL_BASE_PATH, ctx -> {
					getQueryParam(ctx.queryParamMap(), "modeluri")
						.map(this::adaptModelUri)
						.ifPresentOrElse(
							param -> getController(ModelController.class).getOne(ctx, param),
							() -> getController(ModelController.class).getAll(ctx)
						);
				});
				// UPDATE
				patch(ModelServerPaths.MODEL_BASE_PATH, ctx -> {
					getQueryParam(ctx.queryParamMap(), "modeluri")
						.map(this::adaptModelUri)
						.ifPresentOrElse(
							param -> getController(ModelController.class).update(ctx, param),
							() -> handleError(ctx, 400, "Missing parameter 'modeluri'!")
					);
				});
				
				// DELETE
				delete(ModelServerPaths.MODEL_BASE_PATH, ctx -> {
					getQueryParam(ctx.queryParamMap(), "modeluri")
						.map(this::adaptModelUri)
						.ifPresentOrElse(
							param -> getController(ModelController.class).delete(ctx, param),
							() -> handleError(ctx, 400, "Missing parameter 'modeluri'!")
					);
				});
				
				// Execute commands
				patch(ModelServerPaths.EDIT,  ctx -> {
					getQueryParam(ctx.queryParamMap(), "modeluri")
						.map(this::adaptModelUri)
						.ifPresentOrElse(
							param -> getController(ModelController.class).executeCommand(ctx, param),
							() -> handleError(ctx, 400, "Missing parameter 'modeluri'!")
							);
				});

				// GET MODELURIS
				get(ModelServerPaths.MODEL_URIS, getController(ModelController.class).modelUrisHandler);

				get(ModelServerPaths.SCHEMA, getController(SchemaController.class));
				put(ModelServerPaths.SERVER_CONFIGURE, getController(ServerController.class).configureHandler);
				get(ModelServerPaths.SERVER_PING, getController(ServerController.class).pingHandler);
				
				ws(ModelServerPaths.SUBSCRIPTION, wsHandler -> {
					wsHandler.onConnect(ctx ->
						getController(SessionController.class)
							.subscribe(
								ctx,
								getQueryParam(ctx.queryParamMap(), "modeluri").map(this::adaptModelUri).orElse("")
							)
					);
					wsHandler.onClose(ctx -> getController(SessionController.class).unsubscribe(ctx));
					wsHandler.onError(ctx -> {});
					wsHandler.onMessage(ctx -> {});
				});
				
				// TODO: ws for the commands
			});
		});
	}

	private Optional<String> getQueryParam(Map<String, List<String>> queryParams, String paramKey) {
		if (queryParams.containsKey(paramKey))
			return Optional.of(queryParams.get(paramKey).get(0))
					.map(String::toLowerCase);
		
		return Optional.empty();
	}


	private String adaptModelUri(String modelUri) {
		// TODO make case insensitive
		return modelUri
			.replace("file://", "")
			.replace(serverConfiguration.getWorkspaceRoot().toLowerCase(), "");
	}
	
	private void handleError(Context ctx, int statusCode, String errorMsg) {
		LOG.error(errorMsg);
		ctx.status(statusCode).json(JsonResponse.error(errorMsg));
	}
}
