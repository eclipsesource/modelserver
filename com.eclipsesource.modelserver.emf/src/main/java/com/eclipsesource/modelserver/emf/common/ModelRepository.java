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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.eclipsesource.modelserver.command.CCommand;
import com.eclipsesource.modelserver.common.codecs.DecodingException;
import com.eclipsesource.modelserver.edit.CommandCodec;
import com.eclipsesource.modelserver.emf.ResourceManager;
import com.eclipsesource.modelserver.emf.common.codecs.JsonCodec;
import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

/**
 * Injectable singleton class represents a repository of all loaded models and provides a CRUD API.
 *
 */
public class ModelRepository {

	private Map<URI, EObject> models;
	private boolean ensureModelsAreLoaded;

	@Inject
	private ServerConfiguration serverConfiguration;
	@Inject
	private ResourceManager resourceManager;
	@Inject
	private CommandCodec commandCodec;

	private ResourceSet resourceSet = new ResourceSetImpl();
	private EditingDomain domain;

	@Inject
	public ModelRepository(AdapterFactory adapterFactory) {
		this.models = new HashMap<>();
		this.domain = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack(), resourceSet);
	}

	boolean hasModel(String modeluri) {
		final URI uri = createURI(modeluri);
		return models.containsKey(uri);
	}

	public Optional<EObject> getModel(String modeluri) {
		return loadModel(modeluri);
	}

	public Map<URI, EObject> getAllModels() {
		this.ensureModelsAreLoaded();
		return new LinkedHashMap<>(this.models);
	}

	public void addModel(String modeluri, EObject model) {
		this.models.put(createURI(modeluri), model);
	}

	public void updateModel(String modeluri, EObject model) {
		this.models.put(createURI(modeluri), model);
	}
	
	public void updateModel(String modelURI, CCommand command) throws DecodingException {
		Command decoded = commandCodec.decode(domain, command);
		domain.getCommandStack().execute(decoded);
	}

	public void removeModel(String modeluri) {
		this.models.remove(createURI(modeluri));
	}

	public Set<String> getAllModelUris() {
		this.ensureModelsAreLoaded();
		
		Set<String> modeluris = new HashSet<>();
		for(URI uri: this.models.keySet()){
			modeluris.add(uri.toString());
		}
		return modeluris;
	}

	ResourceSet getResourceSet() {
		return resourceSet;
	}

	private Optional<EObject> loadModel(String modeluri) {
		final URI uri = createURI(modeluri);
		if (!this.models.containsKey(uri)) {
			Optional<EObject> model = resourceManager.loadModel(uri, resourceSet, EObject.class);
			if (model.isPresent())
				this.models.put(uri, model.get());
		}
		return Optional.ofNullable(this.models.get(uri));
	}

	private void ensureModelsAreLoaded() {
		if (!this.ensureModelsAreLoaded) {
			serverConfiguration.getWorkspaceEntries().forEach(this::loadModel);
			this.ensureModelsAreLoaded = true;
		}
	}

	private URI createURI(String modeluri) {
		// If the given modeluri is a valid file path it is used to load the model
		modeluri = modeluri.replace("file://", "");
		Path filePath = Paths.get(modeluri);

		// If it is no valid file path it probably is located in the workspace root
		String workspaceBaseURL = serverConfiguration.getWorkspaceRoot();
		if (!Files.exists(filePath) && !modeluri.startsWith(workspaceBaseURL)) {
			modeluri = workspaceBaseURL + modeluri.replaceAll(" ", "");
		}
		return URI.createURI(modeluri);
	}
}
