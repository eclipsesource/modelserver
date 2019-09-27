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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.eclipsesource.modelserver.command.CCommand;
import com.eclipsesource.modelserver.common.ModelServerPaths;
import com.eclipsesource.modelserver.common.codecs.DecodingException;
import com.eclipsesource.modelserver.common.codecs.DefaultJsonCodec;
import com.eclipsesource.modelserver.common.codecs.EncodingException;
import com.eclipsesource.modelserver.common.codecs.XmiCodec;
import com.eclipsesource.modelserver.internal.client.EditingContextImpl;
import com.eclipsesource.modelserver.jsonschema.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ModelServerClient implements ModelServerClientApi<EObject>, ModelServerPaths {

    private static final Set<String> SUPPORTED_FORMATS = ImmutableSet.of("json", "xmi");

    private static Logger LOG = Logger.getLogger(ModelServerClient.class.getSimpleName());

    private OkHttpClient client;
    private String baseUrl;
    private Map<String, WebSocket> openSockets = new LinkedHashMap<>();
    private Map<EditingContextImpl, WebSocket> openEditingSockets = new LinkedHashMap<>();

    public ModelServerClient(String baseUrl) throws MalformedURLException {
        this(new OkHttpClient(), baseUrl);
    }

    public ModelServerClient(OkHttpClient client, String baseUrl) throws MalformedURLException {
        this.client = client;
        this.baseUrl = new URL(baseUrl).toString();
    }

    public void close() {
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }

    @Override
    public CompletableFuture<Response<String>> get(String modelUri) {
        final Request request = new Request.Builder()
            .url(
                createHttpUrlBuilder(makeUrl(MODEL_BASE_PATH))
                    .addQueryParameter("modeluri", modelUri)
                    .build()
            )
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "data"))
            .thenApply(this::getBodyOrThrow);
    }

    @Override
    public CompletableFuture<Response<EObject>> get(String modelUri, String _format) {
    	String format = checkedFormat(_format);

        final Request request = new Request.Builder()
            .url(
                createHttpUrlBuilder(makeUrl(MODEL_BASE_PATH))
                    .addQueryParameter("modeluri", modelUri)
                    .addQueryParameter("format", format)
                    .build()
            )
            .build();

        return call(request)
            .thenApply(resp -> resp.mapBody(body -> body.flatMap(b -> decode(b, format))))
            .thenApply(this::getBodyOrThrow);
    }
    
    private CompletableFuture<Response<Optional<String>>> call(Request request) {
    	return makeCall(request)
    			.thenApply(response -> parseField(response, "data"));
    }

    @Override
    public CompletableFuture<Response<List<String>>> getAll() {
        final Request request = new Request.Builder()
            .url(makeUrl(MODEL_URIS))
            .build();

        return call(request)
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
            .url(
                createHttpUrlBuilder(makeUrl(MODEL_BASE_PATH))
                    .addQueryParameter("modeluri", modelUri)
                    .build()
            )
            .delete()
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "type"))
            .thenApply(this::getBodyOrThrow)
            .thenApply(response -> response.mapBody(body -> body.equals("success")));
    }

    @Override
    public CompletableFuture<Response<String>> update(String modelUri, String updatedModel) {
        final Request request = new Request.Builder()
            .url(
                createHttpUrlBuilder(makeUrl(MODEL_BASE_PATH))
                    .addQueryParameter("modeluri", modelUri)
                    .build()
            )
            .patch(
                RequestBody.create(
                    Json.object(Json.prop("data", Json.text(updatedModel))).toString(), MediaType.parse("application/json"))
            )
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "data"))
            .thenApply(this::getBodyOrThrow);
    }

    @Override
    public CompletableFuture<Response<EObject>> update(String modelUri, EObject updatedModel, String _format) {
    	String format = checkedFormat(_format);
        final Request request = new Request.Builder()
            .url(
                createHttpUrlBuilder(makeUrl(MODEL_BASE_PATH))
                    .addQueryParameter("modeluri", modelUri)
                    .addQueryParameter("format", format)
                    .build()
            )
            .patch(
                RequestBody.create(
                    Json.object(
                        Json.prop("data", Json.text(encode(updatedModel, format)))
                    ).toString(),
                    MediaType.parse("application/json")
                )
            )
            .build();

        return makeCall(request)
            .thenApply(response -> parseField(response, "data"))
            .thenApply(resp -> resp.mapBody(body -> body.flatMap(b -> decode(b, format))))
            .thenApply(this::getBodyOrThrow);
    }
    
    private String checkedFormat(String format) {
        if (Strings.isNullOrEmpty(format)) {
            return "json";
        }
    	
        String result = format.toLowerCase();
        
        if (!isSupportedFormat(result)) {
            throw new CancellationException("Unsupported format "  + format);
        }
        
        return result;
    }

    private boolean isSupportedFormat(String format) {
        return SUPPORTED_FORMATS.contains(format);
    }

    @Override
    public CompletableFuture<Response<Boolean>> save(String modelUri) {
        final Request request = new Request.Builder()
            .url(
                createHttpUrlBuilder(makeUrl(SAVE))
                    .addQueryParameter("modeluri", modelUri)
                    .build()
            )
            .build();

        return makeCall(request)
                .thenApply(response -> parseField(response, "type"))
                .thenApply(this::getBodyOrThrow)
                .thenApply(response -> response.mapBody(body -> body.equals("success")));
    }

    @Override
    public CompletableFuture<Response<String>> getSchema(String modelUri) {
        final Request request = new Request.Builder()
            .url(
                createHttpUrlBuilder(makeUrl(SCHEMA))
                    .addQueryParameter("modeluri", modelUri)
                    .build()
            )
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
	public CompletableFuture<Response<Boolean>> edit(String modelUri, CCommand command, String _format) {
    	String format = checkedFormat(_format);
        final Request request = new Request.Builder()
            .url(
                createHttpUrlBuilder(makeUrl(EDIT))
                    .addQueryParameter("modeluri", modelUri)
                    .addQueryParameter("format", format)
                    .build()
            )
            .patch(
                RequestBody.create(
                    Json.object(
                            Json.prop("data", Json.text(encode(command, format)))
                    ).toString(),
                    MediaType.parse("application/json")
                )
            )
            .build();
        return makeCall(request)
                .thenApply(response -> parseField(response, "type"))
                .thenApply(this::getBodyOrThrow)
                .thenApply(response -> response.mapBody(body -> body.equals("success")));
	}
    

    @Override
    public void subscribe(String modelUri, SubscriptionListener subscriptionListener, String _format) {
    	String format = checkedFormat(_format);
        Request request = new Request.Builder()
            .url(
                makeWsUrl(
                    createHttpUrlBuilder(makeUrl(SUBSCRIPTION))
                        .addQueryParameter("modeluri", modelUri)
                        .addQueryParameter("format", format)
                        .build()
                        .toString()
                )
            )
            .build();
        
        final WebSocket socket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull okhttp3.Response response) {
                subscriptionListener.onOpen(new Response<>(response,
                    body -> require(Optional.ofNullable(body))));
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                Optional<String> type = ModelServerClient.this.parseJsonField(text, "type");
                Optional<String> data = ModelServerClient.this.parseJsonField(text, "data");
                subscriptionListener.onNotification(new ModelServerNotification(type.orElse("unknown"), data));
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
            return webSocket.close(1000, "Websocket closed by client.");
        }
        return false;
    }


    private String makeWsUrl(String path) {
        return path.replaceFirst("http:", "ws:");
    }

    private String makeUrl(String path) {
        return baseUrl + path;
    }

    private HttpUrl.Builder createHttpUrlBuilder(String path) {
        return Objects.requireNonNull(HttpUrl.parse(path)).newBuilder();
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

    private <A> Response<A> getBodyOrThrow(Response<Optional<A>> response) {
        return response.mapBody(this::require);
    }
    
    private <A> A require(Optional<A> value) {
    	return value.orElseThrow(() -> new RuntimeException("Could not parse 'data' field"));
    }

    public String encode(EObject eObject) {
        return encode(eObject, "json");
    }

    public String encode(EObject eObject, String format) {
        try {
            if (format.equals("xmi")) {
                return new XmiCodec().encode(eObject).asText();
            }
            return new DefaultJsonCodec().encode(eObject).toString();
        } catch (EncodingException e) {
            LOG.error("Encoding of " + eObject + " with " + format + " format failed");
            throw new RuntimeException(e);
        }
    }

    public Optional<EObject> decode(String payload) {
        return decode(payload, "json");
    }

    public Optional<EObject> decode(String payload, String format) {
        try {
            if (format.equals("xmi")) {
                return new XmiCodec().decode(payload);
            }
            return new DefaultJsonCodec().decode(payload);
        } catch (DecodingException e) {
            LOG.error("Decoding of " + payload + " with " + format + " format failed");
        }
        return Optional.empty();
    }
    
    @Override
    public EditingContext edit() {
    	EditingContextImpl result;
    	
    	if (!openEditingSockets.isEmpty()) {
    		result = openEditingSockets.keySet().iterator().next();
    		result.retain();
    		return result;
    	}
    	
        Request request = new Request.Builder()
            .url(makeWsUrl(EDIT))
            .build();
        result = new EditingContextImpl(this);
        
        final WebSocket socket = client.newWebSocket(request, result);
        openEditingSockets.put(result, socket);
        
        return result;
    }
    
    @Override
    public boolean close(EditingContext editingContext) {
    	if (((EditingContextImpl)editingContext).release()) {
    		final WebSocket webSocket = openEditingSockets.remove(editingContext);
	        if (webSocket != null) {
                return webSocket.close(1000, "Websocket closed by client.");
	        }
    	}
        return false;
    }
}
