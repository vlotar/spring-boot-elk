# spring-boot-elk
Log Management (ELK) for Spring Boot application
========================

This simple example demonstrates how easy you can enable quite powerful and advanced log management in to your Spring Boot application using Docker.

**Stack of frameworks/technologies:**
* Spring Boot
* Swagger
* Logback
* Logstash
* ElasticSearch
* Kibana
* Docker

_Prerequisites:_
* docker version 1.10.3
* docker-compose version 1.6.2
* JDK8
* Maven

## Start the app

1. In order to run the application in docker you would need to build a docker image first.
This simple maven command will to the trick:
`mvn clean package docker:build -Dmaven.test.skip=true`
<br />_**Note**: for Mac users it can be more difficult.
Make sure that you have Environment variables properly exported.
The easiest way to do this is the following command (https://docs.docker.com/machine/reference/env/): 
`eval $(docker-machine env default)` <br />
DOCKER_TLS_VERIFY=1 <br />
DOCKER_HOST=tcp://192.168.99.100:2376; //you can find this value by typing: `docker-machine ls` <br />
DOCKER_CERT_PATH=/Users/{user}/.docker/machine/certs_ <br />

2. Go to root of the project and run: `docker-compose up -d`
Docker will pull all missing images and start new containers.
After a while you would be ready to use the app.

3. Run: `docker ps -a`. Find an image *"javalogaggregation_web"* and check the exposed port (For example 0.0.0.0:**32808**->8080/tcp).
You would be able to access the application by typing the following URL in the browser:
http://192.168.99.100:32808/swagger-ui.html#!
<br />_**Note**: ip address is a docker machine IP address._
Here you can play with existing REST API.

4. In order to configure Kibana you would need to go to: 
<br />http://localhost:5601/ or http://{$DOCKER_HOST_IP}:5601/
<br />Configure your Kibana and play with your logs.
<br />_**Note**: For Mac users the port 5601 should be added to Port Forwarding Rules of you Docker Virtual Machine_

## Stop the app
Simply type: `docker-compose down`

## Security
user/user
