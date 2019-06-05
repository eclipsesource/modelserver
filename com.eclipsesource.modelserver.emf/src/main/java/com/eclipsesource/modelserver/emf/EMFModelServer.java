/********************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package com.eclipsesource.modelserver.emf;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;
import com.google.common.collect.Maps;
import com.google.inject.Inject;

public class EMFModelServer implements ModelServer {
	private static Logger LOG = Logger.getLogger(EMFModelServer.class);
	@Inject
	private ServerConfiguration serverConfiguration;

	private ConcurrentMap<String, ResourceSet> resourcesSets;

	private ConcurrentMap<String, EObject> openModels;

	@Inject
	private ResourceManager resourceManager;

	public EMFModelServer() {
		resourcesSets = Maps.newConcurrentMap();
		openModels = Maps.newConcurrentMap();
	}

	@Override
	public void initialize() {
		resourceManager.initialize();
	}

	@Override
	public void loadModel(String filePath) {
		LOG.info("Trying to load model from: " + filePath);
		if (openModels.containsKey(filePath)) {
			return;
		}
		String baseURL = serverConfiguration.getWorkspaceRoot();
		if (!filePath.startsWith(baseURL)) {
			filePath = baseURL + "/" + filePath;
		}
		String baseFileName = FilenameUtils.removeExtension(filePath);
		ResourceSet rs = resourcesSets.computeIfAbsent(baseFileName, s -> new ResourceSetImpl());

		final URI uri = URI.createURI(filePath);
		Optional<EObject> root = resourceManager.loadModel(uri, rs, EObject.class);
		if (root.isPresent()) {
			LOG.info("Model has been successfully loaded from resource: " + filePath);
		}

	}

}
