# modelserver [![Build Status](https://travis-ci.org/eclipsesource/modelserver.svg?branch=master)](https://travis-ci.org/eclipsesource/modelserver)
## Build the modelserver
To build and test the components as well as building the modelserver as standalone JAR execute the following maven goal in the root directory:
```bash
mvn clean install
```


## Run the modelserver
### Run the modelserver in an IDE
To run the example modelserver within an IDE, run the main method of [ExampleServerLauncher.java](https://github.com/eclipsesource/modelserver/blob/master/examples/com.eclipsesource.modelserver.example/src/main/java/com/eclipsesource/modelserver/example/ExampleServerLauncher.java) as a Java Application, located in the module `com.eclipsesource.modelserver.example`.


### Run the modelserver standalone JAR
To run the model server standalone JAR, run this command in your terminal:
```bash
cd  examples/com.eclipsesource.modelserver.example/target/
java -jar com.eclipsesource.modelserver.example-X.X.X-SNAPSHOT-standalone.jar
```

### Usage
```
usage: java -jar com.eclipsesource.modelserver.example-X.X.X-SNAPSHOT-standalone.jar
       [-e] [-h] [-p <arg>] [-r <arg>]

options:
 -e,--errorsOnly   Only log errors
 -h,--help         Display usage information about ModelServer
 -p,--port <arg>   Set server port, otherwise default port 8081 is used
 -r,--root <arg>   Set workspace root
```

## Use the modelserver API
If the modelserver is up and running, you can access the modelserver API via `http://localhost:8081/api/v1/*`.

The following table shows the current HTTP endpoints: 

|Category|Description|HTTP method|Path|Input|Examples
|-|-|:-:|-|-|-
|__Models__|Get model|__GET__|`/models/:modeluri`|path parameter: `modeluri`| <ul><li>`/api/v1/models/Coffee.ecore`</li><li>`/api/v1/models/SuperBrewer3000.coffee`</li><li>`/api/v1/models/SuperBrewer3000.json`</li></ul>
| |Get all loaded models|__GET__|`/models`| -
| |Get all loaded model URIs|__GET__|`/modeluris`| -
| |Create new model|__POST__|`/models`|application/json
| |Update model|__PATCH__|`/models/:modeluri`|path parameter: `modeluri` <br> application/json
| |Delete model|__DELETE__|`/models/:modeluri`|path parameter: `modeluri`
|__JSON schema__ |Get JSON schema of a model|__GET__|`/schema/:modeluri`|path parameter: `modeluri`
|__Server actions__|Ping server|__GET__|`/api/v1/server/ping`| -
| |Update server configuration|__PUT__|`/api/v1/server/configure`|application/json

<br>

Subscriptions are implemented via websockets `ws://localhost:8081/api/v1/*`.

The following table shows the current WS endpoints: 

|Description|Path|Input|Returns
|-|-|-|-
|Subscribe to model changes|`/subscribe/:modeluri`|path parameter: `modeluri`|`sessionId`

## Java client API

The modelserver project features a Java-based client API that eases integration with the model server.
The interface declaration looks as follows

```Java
public interface ModelServerClientApiV1 {

    CompletableFuture<Response<String>> get(String modelUri);

    CompletableFuture<Response<List<String>>> getAll();

    CompletableFuture<Response<Boolean>> delete(String modelUri);

    CompletableFuture<Response<String>> update(String modelUri, String updatedModel, String mediaType);

    CompletableFuture<Response<String>> getSchema(String modelUri);

    CompletableFuture<Response<Boolean>> configure(ServerConfiguration configuration);

    CompletableFuture<Response<Boolean>> ping();

    void subscribe(String modelUri);

    boolean unsubscribe(String modelUri);
}

```


### REST API Example

```Java
// You can customize the underlying okhttp instance by passing it in as a 1st parameter 
ModelServerClient client = new ModelServerClient("http://localhost:8081/api/v1/");

// perform simple GET
client.get("SuperBrewer3000.json")
      .thenAccept(response -> System.out.println(response.body()));

// perform same GET, but expect XMI format      
client.get("SuperBrewer3000.json?format=xmi")
      .thenAccept(response -> System.out.println(response.body()));
      
// perform POST
client.update("SuperBrewer3000.json", "{ \"data\": <payload> }")
      .thenAccept(response -> System.out.println(response.body()));      
```

### Subscriptions Example

If you want to be notified about any changes happening on a certain model, 
you can subscribe with a `SubscriptionListener`.

```Java
ModelServerClient client = new ModelServerClient("http://localhost:8081/api/v1/");
String subscriptionId = "SuperBrewer3000.json?format=xmi";
client.subscribe(subscriptionId, new SubscriptionListener() {
  @Override
  public void onOpen(Response<String> response) {
    System.out.println("connected: " + response.getMessage());
  }

  @Override
  public void onMessage(String response) {
    System.out.println("message received: " + response);
  }

  @Override
  public void onClosing(int code, @NotNull String reason) {
    System.out.println("Closing");
  }

  @Override
  public void onFailure(Throwable t) {
    System.out.println("Failed: ");
    t.printStackTrace();
  }

  @Override
  public void onClosed(int code, @NotNull String reason) {
    System.out.println("Connected closed");
  }

  @Override
  public void onFailure(Throwable t, Response<String> response) {
    System.out.println("Failed: " + response);
  }
});
client.unsubscribe(subscriptionId);
```


## Unit Testing

tbd
