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
package com.eclipsesource.modelserver.emf.launch;

import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eclipsesource.modelserver.common.AppEntryPoint;
import com.eclipsesource.modelserver.common.Routing;
import com.google.inject.Inject;

import io.javalin.Javalin;
import io.javalin.JavalinEvent;

public class ModelServerEntryPoint implements AppEntryPoint {
	
	private Javalin app;
	private static final Logger LOG = LoggerFactory.getLogger(ModelServerLauncher.class);

	@SuppressWarnings("rawtypes")
	@Inject(optional = true)
	private Set<Routing> routes = Collections.emptySet();

	@Inject
	public ModelServerEntryPoint(Javalin app) {
		this.app = app;
	}

	public void boot(String[] args) {
		bindRoutes();
		app.event(JavalinEvent.SERVER_START_FAILED, () -> LOG.error("SERVER START FAILED"))
			.port(8081)
			.requestLogger((ctx, timeMs) -> {
				LOG.info(ctx.method() + " "  + ctx.path() + " took " + timeMs + " ms");
			})
			.start();
	}

	private void bindRoutes() {
		routes.forEach(r -> r.bindRoutes());
	}
}
