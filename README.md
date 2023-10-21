# gist-api

This project will build a docker image for an http server that accepts requests to port 8080/{user}

Primary technologies used include 

* Quarkus
* JBang
* Maven
* Wiremock
* Microprofile
* Jakarta EE
* Java 
* Docker
* GraalVM

## Prerequisites

Docker installed and running.

## Building the application

### No Java 8+ installed
If you do not java 8 (required to run maven) or above installed on your PC, you can use the jbang scripts 
(windows, linux or macOS) to install java and run Maven

This will cache all required resources, including java, in the `~/.jbang/cache` directory.

You can run `./jbang cache clear` to remove all of the cache

> Find out more about JBang at https://www.jbang.dev/
 
E.g. to build the application using a container with JBang run
```shell script
./jbang --main org.apache.maven.wrapper.MavenWrapperMain -Dmaven.multiModuleProjectDirectory=./maven .mvn/wrapper/maven-wrapper.jar package -Dnative -Dquarkus.native.container-build=true`
```

All examples below will assume you have java 8+ installed already, but if not and you are using JBang, then replace 
`./mvnw` 
with 
`./jbang --main org.apache.maven.wrapper.MavenWrapperMain -Dmaven.multiModuleProjectDirectory=./maven .mvn/wrapper/maven-wrapper.jar` 
on the commands below. (or the relevant JBang script for your platform .cmd .ps1)  

## Build the Application and create a docker image 

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```

This will firstly produce a native linux executable (irrespective of your platform) by using a docker image to perform the build.

> Please ensure your docker virtual machine has enough resources in order to execute this build. (I'm running with 4GB of RAM)

Then a docker image will be created by executing docker file in the `./src/main/docker/Dockerfile.native` directory

The resulting image should be about 80Meg in size and be named as below

> {username}/ee/gist-api   with tag -> 1.0.0-SNAPSHOT 

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

Or hit this endpoint in your browser <http://localhost:8080/q/health>


## Using the API

To retrieve Gists for a user specify the user on the host/{user}.

<http://localhost:8080/octocat>

This will retrieve a page of gists (defaults to 30 items on a page)

To retrieve a specific page, add a page query parameter e.g. 

<http://localhost:8080/octocat?page=2>

To change the number of items per page add the `per_page` query parameter

<http://localhost:8080/octocat?perpage=2>

You can also use both

<http://localhost:8080/octocat?perpage=3&page=2>
