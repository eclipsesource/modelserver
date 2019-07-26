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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.ecore.EObject;

import java.util.Optional;

public class DefaultJsonCodec implements Codec {

    final EMFJsonConverter emfJsonConverter = new EMFJsonConverter();

    public JsonNode encode(EObject obj) throws EncodingException {
        return encode((Object) obj);
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
}
