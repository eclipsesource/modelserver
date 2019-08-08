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

import com.eclipsesource.modelserver.command.CCommand;
import com.eclipsesource.modelserver.common.codecs.DecodingException;
import com.eclipsesource.modelserver.edit.CommandCodec;
import com.eclipsesource.modelserver.emf.ResourceManager;
import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;
import com.google.inject.Inject;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Injectable singleton class represents a repository of all loaded models and provides a CRUD API.
 *
 */
public class ModelRepository {

	@Inject
	private ServerConfiguration serverConfiguration;
	@Inject
	private ResourceManager resourceManager;
	@Inject
	private CommandCodec commandCodec;

	private ResourceSet resourceSet = new ResourceSetImpl();
	private EditingDomain domain;

	@Inject
	public ModelRepository(AdapterFactory adapterFactory, ServerConfiguration serverConfiguration, ResourceManager resourceManager) {
		this.domain = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack(), resourceSet);
		this.serverConfiguration = serverConfiguration;
		this.resourceManager = resourceManager;
		initialize(serverConfiguration.getWorkspaceRoot());
	}

	public void initialize(String workspaceRoot) {
		resourceSet.getResources().forEach(Resource::unload);
		resourceSet.getResources().clear();
		File workspace = new File(workspaceRoot);
		for (File file : workspace.listFiles()) {
			resourceManager.loadResource(createURI(file.getAbsolutePath()), resourceSet);
		}
		// any resources loaded with errors are probably not resources in the first place
		final List<Resource> resourcesWithErrors = resourceSet.getResources().stream()
			.filter(resource -> !resource.getErrors().isEmpty())
			.collect(Collectors.toList());
		for (Resource resource : resourcesWithErrors) {
			resourceSet.getResources().remove(resource);
		}
	}

	boolean hasModel(String modeluri) {
		final URI uri = createURI(modeluri);
		return resourceSet.getResource(uri, false) != null;
	}

	public Optional<EObject> getModel(String modeluri) {
		return loadResource(modeluri)
			.flatMap(res -> {
				List<EObject> contents = res.getContents();
				if (contents.isEmpty()) {
					return Optional.empty();
				}
				return Optional.of(contents.get(0));
			});
	}

	public Optional<Resource> loadResource(String modeluri) {
		URI uri = createURI(modeluri);
		if (resourceSet.getResource(uri, false) == null) {
			return Optional.empty();
		}
		return Optional.of(resourceSet.getResource(uri, true));
	}

	public Map<URI, EObject> getAllModels() throws IOException {
		EList<Resource> resources = resourceSet.getResources();
		for (Resource resource : resources) {
			resource.load(null);
		}
		LinkedHashMap<URI, EObject> models = new LinkedHashMap<>();
		resources.forEach(resource -> {
			models.put(resource.getURI(), resource.getContents().get(0));
		});
		return models;
	}

	public void addModel(String modeluri, EObject model) throws IOException {
		Resource resource = resourceSet.getResource(createURI(modeluri), true);
		resource.getContents().add(model);
	}

	public Optional<EObject> updateModel(String modeluri, EObject model) {
		return loadResource(modeluri)
			.map(res -> res.getContents().set(0, model));
	}

	public void updateModel(String modelURI, CCommand command) throws DecodingException {
		Command decoded = commandCodec.decode(domain, command);
		domain.getCommandStack().execute(decoded);
	}

	public void removeModel(String modeluri) throws IOException {
		resourceSet.getResource(createURI(modeluri), false).delete(null);
	}

	public boolean saveModel(String modeluri) {
		return this.resourceManager.save(resourceSet);
	}

	public Set<String> getAllModelUris() {
		Set<String> modeluris = new HashSet<>();
		for(Resource resource : resourceSet.getResources()){
			modeluris.add(resource.getURI().deresolve(URI.createFileURI(serverConfiguration.getWorkspaceRoot())).toString());
		}
		return modeluris;
	}

	ResourceSet getResourceSet() {
		return resourceSet;
	}

	private URI createURI(String modeluri) {
		if (modeluri.startsWith("file:")) {
			return URI.createFileURI(modeluri.replaceFirst("file:", ""));
		}

		return URI.createFileURI(modeluri);
	}
}
