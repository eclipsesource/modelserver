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

import com.eclipsesource.modelserver.emf.ResourceManager;
import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;
import com.eclipsesource.modelserver.jsonschema.JsonSchema;
import com.google.inject.Inject;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SchemaController implements Handler {

    @Inject
    private ResourceManager resourceManager;

    @Inject
    private ServerConfiguration serverConfiguration;

    private ResourceSet resourceSet = new ResourceSetImpl();

    @Override
    public void handle(@NotNull Context context) {
        final Optional<EObject> maybeInstance = loadModel(context.pathParam("modeluri"));
        maybeInstance
            .map(instance -> JsonSchema.from(instance.eClass()))
            .map(context::json)
            .orElse(context.status(404));
    }

    private Optional<EObject> loadModel(String filePath) {
        String baseURL = serverConfiguration.getWorkspaceRoot();
        if (!filePath.startsWith(baseURL)) {
            filePath = baseURL + "/" + filePath;
        }

        final URI uri = URI.createURI(filePath);
        return resourceManager.loadModel(uri, resourceSet, EObject.class);
    }
}
