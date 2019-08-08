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
package com.eclipsesource.modelserver.common.codecs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.eclipsesource.modelserver.jsonschema.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class XmiCodec implements Codec {

	private static Logger LOG = Logger.getLogger(XmiCodec.class.getSimpleName());

	public JsonNode encode(EObject eObject) throws EncodingException {
		final Resource resource = createResource();
		resource.getContents().add(EcoreUtil.copy(eObject));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			resource.save(outputStream, null);
		} catch (IOException e) {
			throw new EncodingException(e);
		}

		return Json.text(outputStream.toString());
	}

	@Override
	public Optional<EObject> decode(String payload) throws DecodingException {
		ResourceSet resourceSet = new ResourceSetImpl();
		Optional<Resource> resource = decode(resourceSet, "virtual.xmi", payload);
		return resource.map(r -> r.getContents().isEmpty() ? null : r.getContents().get(0));
	}

	private Resource createResource() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(null, new XMIResourceFactoryImpl());
		return resourceSet.createResource(URI.createURI("virtual.xmi"));
	}

	@Override
	public Optional<Resource> decode(ResourceSet resourceSet, String modelURI, String payload)
			throws DecodingException {

		URI uri = URI.createURI(modelURI);
		Resource result = resourceSet.getResource(uri, false);
		if (result != null && !(result instanceof XMIResource)) {
			// Replace it
			LOG.warn(String.format("Replacing resource '%s' with a XMIResource", modelURI));
			result.unload();
			resourceSet.getResources().remove(result);
			result = null;
		}
		if (result == null) {
			result = new XMIResourceFactoryImpl().createResource(URI.createURI(modelURI));
			resourceSet.getResources().add(result);
		}
		try {
			result.load(new ByteArrayInputStream(payload.getBytes()), resourceSet.getLoadOptions());
		} catch (IOException e) {
			throw new DecodingException(e);
		}

		return Optional.of(result);
	}

}
