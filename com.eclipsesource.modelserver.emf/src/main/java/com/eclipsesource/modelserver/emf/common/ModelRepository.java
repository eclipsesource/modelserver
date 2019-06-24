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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

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
		this.models = new HashMap<URI, EObject>();
	}

	public Optional<EObject> getModel(String modeluri) {
		Optional<EObject> model = loadModel(modeluri);
		return model;
	}

	public Set<Entry<URI, EObject>> getAllModels() {
		return this.models.entrySet();
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
		Set<String> modelUris = new HashSet<String>();
		for(URI uri: this.models.keySet()){
			modelUris.add(uri.toString());
		}
		return modelUris;
	}

	private Optional<EObject> loadModel(String modeluri) {
		final URI uri = getWorkspaceUri(modeluri);
		this.models.computeIfAbsent(uri, m -> resourceManager.loadModel(m, resourceSet, EObject.class).orElse(null));
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
