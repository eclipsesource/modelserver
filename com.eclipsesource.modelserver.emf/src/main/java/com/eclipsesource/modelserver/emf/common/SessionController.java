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

import static java.util.stream.Collectors.toSet;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import com.eclipsesource.modelserver.jsonschema.Json;
import com.google.common.collect.Maps;
import com.google.inject.Inject;

import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsHandler;

public class SessionController extends WsHandler {

	private Map<String, Set<WsContext>> modelUrisToClients = Maps.newConcurrentMap();

	@Inject
	private ModelRepository modelRepository;

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

	public void modelChanged(String modeluri) {
		modelRepository.getModel(modeluri).ifPresentOrElse(
				eObject -> broadcastModelUpdate(modeluri, eObject),
				() -> broadcastError(modeluri, "Could not load changed object")
		);
	}

	public void modelDeleted(String modeluri) {
		broadcastModelUpdate(modeluri, null);
	}
	
	private Stream<WsContext> getOpenSessions(String modeluri) {
		return modelUrisToClients.get(modeluri).stream().filter(ctx -> ctx.session.isOpen());
	}

	private void broadcastModelUpdate(String modeluri, @Nullable EObject updatedModel) {
		if (modelUrisToClients.containsKey(modeluri)) {
			getOpenSessions(modeluri)
				.forEach(session -> session.send(JsonResponse.data(updatedModel)));
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