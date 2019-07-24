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

public class JsonResponse {

	public static ObjectNode success() {
		return Json.object(
			Json.prop("type", Json.text("success"))
		);
	}

	public static JsonNode success(ObjectNode response) {
		return Json.merge(success(), response);
	}

	public static ObjectNode error() {
		return Json.object(
			Json.prop("type", Json.text("error"))
		);
	}

	public static ObjectNode error(String message) {
		return (ObjectNode) Json.merge(
			error(),
			Json.object(Json.prop("message", Json.text(message)))
		);
	}

	public static JsonNode data(@Nullable JsonNode jsonNode) {
		return Json.object(
			Json.prop("data", jsonNode == null ? NullNode.getInstance() : jsonNode)
		);
	}

	public static JsonNode confirm(String message) {
		return Json.object(
			Json.prop("type", Json.text("confirm")),
			Json.prop("message", Json.text(message))
		);
	}
}
