# modelserver [![Build Status](https://travis-ci.org/eclipsesource/modelserver.svg?branch=master)](https://travis-ci.org/eclipsesource/modelserver)
## Build the modelserver
To build and test the components execute the following maven goal in the root directory:
```bash
mvn clean install
```
To build the modelserver as standalone JAR execute the following maven goal in the root directory:
```bash
mvn clean install -Pfatjar
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

### Modelserver port
For running the modelserver the default port is `8081`.
To change the port, use the command line argument `--port=XXXX`.

## Use the modelserver API
If the modelserver is up and running, you can access the modelserver API via `http://localhost:8081/api/model/:modeluri`.

The following example models can be requested and are returned as a whole in JSON format:
 - `/api/model/Coffe.ecore`
 - `/api/model/SuperBrewer3000.coffee`
 - `/api/model/SuperBrewer3000.json`