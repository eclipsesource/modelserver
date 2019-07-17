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
import org.eclipse.emf.ecore.EObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Encoder {

    private Map<String, Codec> formatToCodec = new LinkedHashMap<>();

    public Encoder() {
        formatToCodec.put("xmi", new XmiCodec());
        formatToCodec.put("json", new JsonCodec());
    }

    public JsonNode encode(Context context, EObject eObject) throws EncodingException {
        return findFormat(context).encode(eObject);
    }

    private Codec findFormat(Context ctx) {
        return Optional
            .ofNullable(ctx.queryParam("format"))
            .flatMap(f -> Optional.ofNullable(formatToCodec.get(f)))
            .orElse(new JsonCodec());
    }
}
