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

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import io.javalin.Javalin;

public class ModelServerModule extends AbstractModule {

	private Javalin app;

	private ModelServerModule(Javalin app) {
		this.app = app;
	}

	public static ModelServerModule create() {
		return new ModelServerModule(Javalin.create());
	}

	@Override
	protected void configure() {
		bind(Javalin.class).toInstance(app);
		bind(ModelServerStartup.class).in(Singleton.class);
	}
}
