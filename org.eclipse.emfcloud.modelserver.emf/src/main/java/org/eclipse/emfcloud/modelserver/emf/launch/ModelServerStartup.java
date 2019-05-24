/********************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.eclipse.emfcloud.modelserver.emf.launch;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emfcloud.modelserver.common.AppEntryPoint;
import org.eclipse.emfcloud.modelserver.common.EntryPointType;
import com.google.inject.Inject;

public class ModelServerStartup {

   @Inject(optional = true)
   private final Map<EntryPointType, AppEntryPoint> entryPoints = Collections.emptyMap();

   public void boot(final EntryPointType entryPointType, final int port) {
      Optional<AppEntryPoint> entryPoint = Optional.ofNullable(entryPoints.get(entryPointType));
      entryPoint.orElseThrow(() -> new RuntimeException("EntryPoint not defined")).boot(port);
   }
}
