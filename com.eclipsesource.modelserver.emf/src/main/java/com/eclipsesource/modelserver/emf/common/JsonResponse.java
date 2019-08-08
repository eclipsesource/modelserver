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

import com.eclipsesource.modelserver.jsonschema.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.Nullable;

/**
 * Structure of JsonResponse
 * 
 * {
 *   "type": "success" | "error" | "fullUpdate" | "incrementalUpdate" | "dirtyState"
 *   "data": String | Boolean | EObject
 * }
 */

public class JsonResponse {
	
	private static ObjectNode type(String type) {
		// Allowed types: success, error, fullUpdate, incrementalUpdate
		return Json.object(Json.prop("type", Json.text(type)));
	}
	
	private static JsonNode data(@Nullable JsonNode jsonNode) {
		return Json.object(
			Json.prop("data", jsonNode == null ? NullNode.getInstance() : jsonNode)
		);
	}

	private static JsonNode data(String message) {
		return Json.object(
			Json.prop("data", Json.text(message))
		);
	}

	private static JsonNode data(Boolean b) {
		return Json.object(
			Json.prop("data", Json.bool(b))
		);
	}

	public static ObjectNode success() {
		return type("success");
	}

	public static JsonNode success(@Nullable JsonNode jsonNode) {
		return Json.merge(success(), data(jsonNode));
	}
	
	public static JsonNode success(String message) {
		return Json.merge(success(), data(message));
	}

	public static ObjectNode error() {
		return type("error");
	}

	public static JsonNode error(String message) {
		return Json.merge(error(), data(message));
	}

	public static JsonNode fullUpdate(@Nullable JsonNode jsonNode) {
		return Json.merge(type("fullUpdate"), data(jsonNode));
	}

	public static JsonNode incrementalUpdate(@Nullable JsonNode jsonNode) {
		return Json.merge(type("incrementalUpdate"), data(jsonNode));
	}
	
	public static JsonNode dirtyState(Boolean isDirty) {
		return Json.merge(type("dirtyState"), data(isDirty));
	}
}
