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
package com.eclipsesource.modelserver.client;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

import com.eclipsesource.modelserver.command.CCommand;
import com.eclipsesource.modelserver.common.codecs.DecodingException;
import com.eclipsesource.modelserver.common.codecs.XmiCodec;

public class XmiToEObjectSubscriptionListener extends TypedSubscriptionListener<EObject> {
	public XmiToEObjectSubscriptionListener() {
		super(XmiToEObjectSubscriptionListener::decode);
	}

	@Override
	public void onIncrementalUpdate(EObject command) {
		if (!(command instanceof CCommand)) {
			throw new IllegalArgumentException("Expected CCommand but received: " + command);
		}
		onIncrementalUpdate((CCommand) command);
	}

	public void onIncrementalUpdate(CCommand command) {
	}

	public static Optional<EObject> decode(String xmiString) {
		try {
			return new XmiCodec().decode(xmiString);
		} catch (DecodingException e) {
			throw new RuntimeException(e);
		}
	}
}
