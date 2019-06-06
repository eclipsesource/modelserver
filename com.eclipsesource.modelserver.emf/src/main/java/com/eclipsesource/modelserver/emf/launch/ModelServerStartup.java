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
import java.util.Map;
import java.util.Optional;

import com.eclipsesource.modelserver.common.AppEntrypoint;
import com.eclipsesource.modelserver.common.EntrypointType;
import com.google.inject.Inject;

public class ModelServerStartup {

	@Inject(optional = true)
	private Map<EntrypointType, AppEntrypoint> entrypoints = Collections.emptyMap();

	public void boot(EntrypointType entrypointType, String[] args) {
		Optional<AppEntrypoint> entryPoint = Optional.ofNullable(entrypoints.get(entrypointType));
		entryPoint.orElseThrow(() -> new RuntimeException("Entrypoint not defined")).boot(args);;
	}
}