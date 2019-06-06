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

package com.eclipsesource.modelserver.emf.di;

import java.util.Collection;

import com.eclipsesource.modelserver.common.AppEntrypoint;
import com.eclipsesource.modelserver.common.EntrypointType;
import com.eclipsesource.modelserver.common.Routing;
import com.eclipsesource.modelserver.emf.EMFModelServer;
import com.eclipsesource.modelserver.emf.ModelServer;
import com.eclipsesource.modelserver.emf.ResourceManager;
import com.eclipsesource.modelserver.emf.configuration.EPackageConfiguration;
import com.eclipsesource.modelserver.emf.configuration.EcorePackageConfiguration;
import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;
import com.eclipsesource.modelserver.emf.launch.ModelServerController;
import com.eclipsesource.modelserver.emf.launch.ModelServerEntrypoint;
import com.eclipsesource.modelserver.emf.launch.ModelServerRouting;
import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;

public class EMFModelServerModule extends AbstractModule {
	protected Multibinder<EPackageConfiguration> ePackageConfigurationBinder;

	@Override
	protected void configure() {
		bind(ServerConfiguration.class).in(Singleton.class);
		bind(ModelServer.class).to(bindModelServer());
		ePackageConfigurationBinder = Multibinder.newSetBinder(binder(), EPackageConfiguration.class);
		bind(ResourceManager.class).in(Singleton.class);
		bindEPackageConfigurations().forEach(c -> ePackageConfigurationBinder.addBinding().to(c));

		bind(ModelServerController.class).in(Singleton.class);
		Multibinder.newSetBinder(binder(), Routing.class).addBinding().to(ModelServerRouting.class).in(Singleton.class);
		MapBinder.newMapBinder(binder(), EntrypointType.class, AppEntrypoint.class).addBinding(EntrypointType.REST).to(ModelServerEntrypoint.class);
	}

	protected Class<? extends ModelServer> bindModelServer() {
		return EMFModelServer.class;
	}

	protected Collection<Class<? extends EPackageConfiguration>> bindEPackageConfigurations() {
		return Lists.newArrayList(EcorePackageConfiguration.class);
	}
}
