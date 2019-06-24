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

import org.jetbrains.annotations.Nullable;

import com.eclipsesource.modelserver.jsonschema.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.javalin.plugin.json.JavalinJackson;

public class JsonResponse {

	public static JsonNode success(ObjectNode response) {
		final ObjectNode success = Json.object(
			Json.prop("type", Json.text("success"))
		);
		return Json.merge(success, response);
	}

	public static ObjectNode error(String message) {
		return Json.object(
			Json.prop("type", Json.text("error")),
			Json.prop("message", Json.text(message))
		);
	}

	public static JsonNode data(@Nullable Object someObject) {
		return Json.object(
			Json.prop("data", someObject != null ? JavalinJackson.getObjectMapper().valueToTree(someObject) : Json.text(""))
		);
	}

	public static JsonNode confirm(String message) {
		return Json.object(
			Json.prop("type", Json.text("confirm")),
			Json.prop("message", Json.text(message))
		);
	}
}
