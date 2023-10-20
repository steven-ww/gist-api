# gist-api

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Prerequisites

Docker installed and running.

## Building the application

### No Java 8+ installed
If you do not java java 8 or above installed on your PC, you can use the jbang scripts (windows, linux or macOS) to 
install java and run Maven

This will cache all required resources, including java, in the `~/.jbang/cache` directory.

You can run `./jbang cache clear` to remove all of the cache

> Find out more about JBang at https://www.jbang.dev/
 
E.g. to build the application using a container with jbang run
```shell script
./jbang --main org.apache.maven.wrapper.MavenWrapperMain -Dmaven.multiModuleProjectDirectory=./maven .mvn/wrapper/maven-wrapper.jar package -Dnative -Dquarkus.native.container-build=true`
```

All examples below will assume you have java 8+ installed already, but if not and you are using JBang, then replace 
`./mvnw` 
with 
`./jbang --main org.apache.maven.wrapper.MavenWrapperMain -Dmaven.multiModuleProjectDirectory=./maven .mvn/wrapper/maven-wrapper.jar` 
on the commands below. (or the relevant jbang script for your platform .cmd .ps1)  

## Build the Application and create a docker image 

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```

This will firstly produce a native linux executable (irrespective of your platform) by using a docker image to perform the build.

> Please ensure your docker virtual machine has enough resources in order to execute this build. (I'm running with 4GB of RAM)

Then a docker image will be created by executing docker file in the ./src/main/docker/Dockerfile.native directory

The resulting image should be about 78Meg in size and be named as below

> {username}/ee/gist-api   1.0.0-SNAPSHOT 

## Run the application

```shell script
docker run -i --rm -p 8080:8080 {username}/ee/gist-api:1.0.0-SNAPSHOT
```
> run `docker image ls` to see a list of the images 

## Confirm the server is up 

Confirm the server is up and accepting requests by running (hits the health check endpoint)

```shell script
curl localhost:8080/q/health
```

Or hit this endpoint in your browser `http://localhost:8080/q/health`





## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```



The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/gist-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
