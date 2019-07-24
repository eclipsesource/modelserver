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

import com.eclipsesource.modelserver.common.ModelServerPaths;
import com.eclipsesource.modelserver.jsonschema.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class ModelServerClient implements ModelServerClientApi, ModelServerPaths {

    private static Logger LOG = Logger.getLogger(ModelServerClient.class.getSimpleName());

    private OkHttpClient client;
    private String baseUrl;
    private Map<String, WebSocket> openSockets = new LinkedHashMap<>();

    public ModelServerClient(String baseUrl) throws MalformedURLException {
        this(new OkHttpClient(), baseUrl);
    }

    public ModelServerClient(OkHttpClient client, String baseUrl) throws MalformedURLException {
        this.client = client;
        this.baseUrl = new URL(baseUrl).toString();
    }

    @Override
    public CompletableFuture<Response<String>> get(String modelUri) {
        final Request request = new Request.Builder()
            .url(makeUrl(MODEL_CRUD).replace(":modeluri", modelUri))
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "data"))
            .thenApply(this::getBodyOrThrow);
    }

    @Override
    public CompletableFuture<Response<List<String>>> getAll() {
        final Request request = new Request.Builder()
            .url(makeUrl(MODEL_URIS))
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "data"))
            .thenApply(this::getBodyOrThrow)
            .thenApply(response -> response.mapBody(body -> {
                List<String> uris = new ArrayList<>();
                try {
                    Json.parse(body).forEach(uri -> uris.add(uri.textValue()));
                    return uris;
                } catch (IOException e) {
                    throw new CompletionException(e);
                }
            }));
    }

    @Override
    public CompletableFuture<Response<Boolean>> delete(String modelUri) {
        final Request request = new Request.Builder()
            .url(makeUrl(MODEL_CRUD).replace(":modeluri", modelUri))
            .delete()
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "type"))
            .thenApply(this::getBodyOrThrow)
            .thenApply(response -> response.mapBody(body -> body.equals("confirm")));
    }

    @Override
    public CompletableFuture<Response<String>> update(String modelUri, String updatedModel, String mediaType) {
        final Request request = new Request.Builder()
            .url(makeUrl(MODEL_CRUD).replace(":modeluri", modelUri))
            .patch(RequestBody.create(updatedModel, MediaType.parse(mediaType)))
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "data"))
            .thenApply(this::getBodyOrThrow);
    }

    @Override
    public CompletableFuture<Response<String>> getSchema(String modelUri) {
        final Request request = new Request.Builder()
            .url(makeUrl(SCHEMA).replace(":modeluri", modelUri))
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "data"))
            .thenApply(this::getBodyOrThrow);
    }

    @Override
    public CompletableFuture<Response<Boolean>> configure(ServerConfiguration configuration) {

        ObjectNode config = Json.object(
            Json.prop("workspaceRoot", Json.text(configuration.getWorkspaceRoot()))
        );

        final Request request = new Request.Builder()
            .url(makeUrl(SERVER_CONFIGURE))
            .put(RequestBody.create(config.toString(), MediaType.parse("application/json")))
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "type"))
            .thenApply(this::getBodyOrThrow)
            .thenApply(response -> response.mapBody(body -> body.equals("success")));
    }

    @Override
    public CompletableFuture<Response<Boolean>> ping() {
        final Request request = new Request.Builder()
            .url(makeUrl(SERVER_PING))
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "type"))
            .thenApply(this::getBodyOrThrow)
            .thenApply(response -> response.mapBody(body -> body.equals("success")));
    }

    @Override
    public void subscribe(String modelUri, SubscriptionListener subscriptionListener) {
        final String queryParams = modelUri.contains("?") ? modelUri.substring(modelUri.indexOf("?")) : "";
        Request request = new Request.Builder()
            .url(makeUrl(SUBSCRIPTION)
                .replace("http", "ws")
                .replace(":modeluri", modelUri.substring(0, modelUri.indexOf("?")))
                .concat(queryParams)
            )
            .build();
        final WebSocket socket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull okhttp3.Response response) {
                subscriptionListener.onOpen(new Response<>(response));
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                ModelServerClient.this.parseJsonField(text, "data")
                    .ifPresent(subscriptionListener::onMessage);
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                subscriptionListener.onClosing(code, reason);
            }

            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                subscriptionListener.onClosed(code, reason);
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable okhttp3.Response response) {
                if (response != null) {
                    subscriptionListener.onFailure(t, new Response<>(response));
                } else {
                    subscriptionListener.onFailure(t);
                }
            }
        });
        openSockets.put(modelUri, socket);
    }

    public boolean unsubscribe(String modelUri) {
        final WebSocket webSocket = openSockets.get(modelUri);
        if (webSocket != null) {
            final boolean closed = webSocket.close(1000, "Websocket closed by client.");
            client.dispatcher().executorService().shutdown();
            client.connectionPool().evictAll();
            return closed;
        }
        return false;
    }


    private String makeUrl(String path) {
        return baseUrl + path;
    }

    private CompletableFuture<Response<String>> makeCall(final Request request) {
        CompletableFuture<Response<String>> future = new CompletableFuture<>();
        this.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) {
                future.complete(new Response<>(response));
            }
        });

        return future;
    }

    private Response<Optional<String>> parseField(Response<String> response, String field) {
        return response.mapBody(body -> parseJsonField(body, field));
    }

    private Optional<String> parseJsonField(String jsonAsString, String field) {
        try {
            final JsonNode data = Json.parse(jsonAsString).get(field);
            if (data == null) {
                return Optional.empty();
            }
            if (data.isTextual()) {
                return Optional.of(data.textValue());
            }
            return Optional.of(data.toString());
        } catch (IOException e) {
            LOG.error("Could not parse JSON", e);
            return Optional.empty();
        }
    }

    private Response<String> getBodyOrThrow(Response<Optional<String>> response) {
        return response
            .mapBody(maybeBody ->
                maybeBody.orElseThrow(() -> new RuntimeException("Could not parse 'data' field"))
            );
    }
}
