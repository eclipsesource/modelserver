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

import org.jetbrains.annotations.NotNull;

public interface TypedSubscriptionListener<A> {
    void onOpen(Response<A> response);

    void onMessage(A response);

    void onClosing(int code, @NotNull String reason);

    void onClosed(int code, @NotNull String reason);

    void onFailure(Throwable t, Response<String> response);

    void onFailure(Throwable t);
}

