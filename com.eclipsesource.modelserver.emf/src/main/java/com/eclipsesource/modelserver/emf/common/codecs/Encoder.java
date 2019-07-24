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
package com.eclipsesource.modelserver.emf.common.codecs;

import com.fasterxml.jackson.databind.JsonNode;
import io.javalin.http.Context;
import io.javalin.websocket.WsContext;
import org.eclipse.emf.ecore.EObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Encoder {

    private Map<String, Codec> formatToCodec = new LinkedHashMap<>();

    public Encoder() {
        formatToCodec.put("xmi", new XmiCodec());
        formatToCodec.put("json", new JsonCodec());
    }

    public JsonNode encode(Context context, EObject eObject) throws EncodingException {
        return findFormat(context.queryParamMap()).encode(eObject);
    }

    public JsonNode encode(WsContext context, EObject eObject) throws EncodingException {
        return findFormat(context.queryParamMap()).encode(eObject);
    }

    private Codec findFormat(Map<String, List<String>> queryParams) {
        return Optional
            .ofNullable(queryParams.get("format"))
            .filter(list -> !list.isEmpty())
            .flatMap(f -> Optional.ofNullable(formatToCodec.get(f.get(0))))
            .orElse(new JsonCodec());
    }
}
