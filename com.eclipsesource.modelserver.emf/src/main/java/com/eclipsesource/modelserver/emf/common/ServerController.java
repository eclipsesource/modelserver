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

import org.apache.log4j.Logger;

import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;
import com.google.inject.Inject;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ServerController {
	Logger LOG = Logger.getLogger(ServerController.class);
	@Inject
	private ServerConfiguration serverConfiguration;

	public Handler pingHandler = ctx -> {
		ctx.json("Success");
	};

	public Handler configureHandler = ctx -> {
		ServerConfiguration newConf = ctx.bodyAsClass(ServerConfiguration.class);
		String workspaceRoot = newConf.getWorkspaceRoot();
		if (workspaceRoot != null) {
			if (ServerConfiguration.isValidWorkspaceRoot(workspaceRoot)) {
				serverConfiguration.setWorkspaceRoot(workspaceRoot);
			} else {
				handleError(ctx, 400, "The given workspaceRoot is not a valid path: " + workspaceRoot);
			}
		}
	};

	private void handleError(Context ctx, int statusCode, String errorMsg) {
		LOG.error(errorMsg);
		ctx.status(statusCode).result(errorMsg);
	}

}
