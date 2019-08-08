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

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emfjson.jackson.errors.JSONException;
import org.emfjson.jackson.resource.JsonResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class DefaultJsonCodec implements Codec {

	private static Logger LOG = Logger.getLogger(DefaultJsonCodec.class.getSimpleName());

	final EMFJsonConverter emfJsonConverter = new EMFJsonConverter();

	public JsonNode encode(EObject obj) throws EncodingException {
		return encode((Object) obj, getObjectMapper());
	}

	@Override
	public Optional<EObject> decode(String payload) {
		return emfJsonConverter.fromJson(payload);
	}

	public static JsonNode encode(Object obj) throws EncodingException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.valueToTree(obj);
		} catch (IllegalArgumentException ex) {
			throw new EncodingException(ex);
		}
	}

	public static JsonNode encode(Object obj, ObjectMapper mapper) throws EncodingException {
		try {
			return mapper.valueToTree(obj);
		} catch (IllegalArgumentException ex) {
			throw new EncodingException(ex);
		}
	}

	protected ObjectMapper getObjectMapper() {
		return emfJsonConverter.getMapper();
	}

	@Override
	public Optional<Resource> decode(ResourceSet resourceSet, String modelURI, String payload)
			throws DecodingException {

		URI uri = URI.createURI(modelURI);
		Resource result = resourceSet.getResource(uri, false);
		if (result != null && !(result instanceof JsonResource)) {
			// Replace it
			LOG.warn(String.format("Replacing resource '%s' with a JsonResource", modelURI));
			result.unload();
			resourceSet.getResources().remove(result);
			result = null;
		}

		if (result == null) {
			result = new JsonResource(uri, getObjectMapper());
			resourceSet.getResources().add(result);
		}

		try (InputStream input = new ByteArrayInputStream(payload.getBytes())) {
			result.load(input, resourceSet.getLoadOptions());
		} catch (IOException e) {
			result.getErrors().add(new JSONException(e, JsonLocation.NA));
		}

		return Optional.of(result);
	}

}
