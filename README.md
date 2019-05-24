# modelserver [![Build Status](https://travis-ci.org/eclipsesource/modelserver.svg?branch=master)](https://travis-ci.org/eclipsesource/modelserver)
## Build
To build and test the components exectute the following  the root directory:
```bash
mvn clean install
```

## Build and run the standalone jar
```bash
mvn clean install -Pfatjar
cd com.eclipsesource.modelserver.emf/target/
java -jar com.eclipsesource.modelserver.emf-X.X.X-SNAPSHOT-standalone.jar
```
