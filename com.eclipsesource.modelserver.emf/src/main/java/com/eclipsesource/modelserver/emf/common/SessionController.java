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

import com.eclipsesource.modelserver.emf.common.codecs.Encoder;
import com.eclipsesource.modelserver.emf.common.codecs.EncodingException;
import com.eclipsesource.modelserver.jsonschema.Json;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import io.javalin.http.Context;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsHandler;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class SessionController extends WsHandler {

	Logger LOG = Logger.getLogger(SessionController.class.getSimpleName());

	private Map<String, Set<WsContext>> modelUrisToClients = Maps.newConcurrentMap();

	@Inject
	private ModelRepository modelRepository;

	private Encoder encoder;

	public SessionController() {
		this.encoder = new Encoder();
	}

	public void subscribe(WsContext ctx, String modeluri) {
		modelUrisToClients.computeIfAbsent(modeluri, clients -> ConcurrentHashMap.newKeySet()).add(ctx);
		ctx.send(JsonResponse.success(Json.object(Json.prop("sessionId", Json.text(ctx.getSessionId())))));
	}

	public void unsubscribe(WsContext ctx) {
		Iterator<Map.Entry<String, Set<WsContext>>> it = modelUrisToClients.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, Set<WsContext>> entry = it.next();
			Set<WsContext> clients = entry.getValue();
			clients.remove(ctx);
			if (clients.isEmpty()) {
				it.remove();
			}
		}
	}

	public void modelChanged(String modeluri, Context context) {
		modelRepository.getModel(modeluri).ifPresentOrElse(
				eObject -> broadcastModelUpdate(modeluri, eObject, context),
				() -> broadcastError(modeluri, "Could not load changed object")
		);
	}

	public void modelDeleted(String modeluri, Context context) {
		broadcastModelUpdate(modeluri, null, context);
	}
	
	private Stream<WsContext> getOpenSessions(String modeluri) {
		return modelUrisToClients.get(modeluri).stream().filter(ctx -> ctx.session.isOpen());
	}

	private void broadcastModelUpdate(String modeluri, @Nullable EObject updatedModel, Context context) {
		if (modelUrisToClients.containsKey(modeluri)) {
			getOpenSessions(modeluri)
				.forEach(session -> {
					try {
						session.send(JsonResponse.data(encoder.encode(context, updatedModel)));
					} catch (EncodingException e) {
						LOG.error("Broadcast model update of " + modeluri + " failed", e);
					}
				});
		}
	}

	private void broadcastError(String modeluri, String errorMessage) {
		getOpenSessions(modeluri)
			.forEach(session -> session.send(JsonResponse.error(errorMessage)));
	}

	@TestOnly
	boolean isClientSubscribed(WsContext ctx) {
		return ! modelUrisToClients.entrySet().stream().filter(entry -> entry.getValue().contains(ctx)).collect(toSet()).isEmpty();
	}
}
