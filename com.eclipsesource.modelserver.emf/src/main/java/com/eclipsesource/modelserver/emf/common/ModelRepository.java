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

import java.util.*;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.eclipsesource.modelserver.emf.ResourceManager;
import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;
import com.google.inject.Inject;

/**
 * Injectable singleton class represents a repository of all loaded models and provides a CRUD API.
 *
 */
public class ModelRepository {

	private Map<URI, EObject> models;

	@Inject
	private ServerConfiguration serverConfiguration;
	@Inject
	private ResourceManager resourceManager;

	private ResourceSet resourceSet = new ResourceSetImpl();

	@Inject
	public ModelRepository() {
		this.models = new HashMap<>();
	}

	boolean hasModel(String modeluri) {
		final URI uri = getWorkspaceUri(modeluri);
		return models.containsKey(uri);
	}

	public Optional<EObject> getModel(String modeluri) {
		return loadModel(modeluri);
	}

	public Map<URI, EObject> getAllModels() {
		return new LinkedHashMap<>(this.models);
	}

	public void addModel(String modeluri, EObject model) {
		this.models.put(getWorkspaceUri(modeluri), model);
	}

	public void updateModel(String modeluri, EObject model) {
		this.models.put(getWorkspaceUri(modeluri), model);
	}

	public void removeModel(String modeluri) {
		this.models.remove(getWorkspaceUri(modeluri));
	}

	public Set<String> getAllModelUris() {
		Set<String> modeluris = new HashSet<>();
		for(URI uri: this.models.keySet()){
			modeluris.add(uri.toString());
		}
		return modeluris;
	}

	private Optional<EObject> loadModel(String modeluri) {
		final URI uri = getWorkspaceUri(modeluri);
		if (!this.models.containsKey(uri)) {
			Optional<EObject> model = resourceManager.loadModel(uri, resourceSet, EObject.class);
			if (model.isPresent())
				this.models.put(uri, model.get());
		}
		return Optional.ofNullable(this.models.get(uri));
	}

	private URI getWorkspaceUri(String modeluri) {
		String baseURL = serverConfiguration.getWorkspaceRoot();
		if (!modeluri.startsWith(baseURL)) {
			modeluri = baseURL + "/" + modeluri.replaceAll(" ", "");
		}
		return URI.createURI(modeluri);
	}

}
