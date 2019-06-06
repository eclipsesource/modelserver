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
package com.eclipsesource.modelserver.common;

import com.google.inject.Inject;
import com.google.inject.Injector;

import java.lang.reflect.ParameterizedType;

public abstract class Routing<T> {
	@Inject
	private Injector injector;

	private Class<T> controller;

	protected Routing() {
	}

	public abstract void bindRoutes();

	public T getController() {
		return injector.getInstance(getControllerFromGenericType());
	}

	@SuppressWarnings("unchecked")
	private Class<T> getControllerFromGenericType() {
		if (controller == null) {
			controller = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return controller;
	}
}
