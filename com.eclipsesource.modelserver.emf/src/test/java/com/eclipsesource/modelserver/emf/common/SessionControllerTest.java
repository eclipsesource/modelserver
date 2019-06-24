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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;

import io.javalin.websocket.WsContext;

public class SessionControllerTest {

	SessionController sessionController = new SessionController();
	final WsContext clientCtx = mock(WsContext.class);

	@Test
	public void testSubscribe() {
		when(clientCtx.send(anyString())).thenReturn(CompletableFuture.completedFuture(null));
		when(clientCtx.getSessionId()).thenReturn(UUID.randomUUID().toString());
		when(clientCtx.pathParam("modeluri")).thenReturn("fancytesturi");

		assertFalse(sessionController.isClientSubscribed(clientCtx));

		sessionController.subscribe(clientCtx, clientCtx.pathParam("modeluri"));

		assertTrue(sessionController.isClientSubscribed(clientCtx));
	}

	@Test()
	public void testUnsubscribe() {
		testSubscribe();
		assertTrue(sessionController.isClientSubscribed(clientCtx));

		sessionController.unsubscribe(clientCtx);

		assertFalse(sessionController.isClientSubscribed(clientCtx));
	}
}
