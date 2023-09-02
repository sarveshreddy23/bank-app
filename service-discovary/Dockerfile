FROM openjdk:17-jdk-slim as build
MAINTAINER sarvesh

COPY target/*.jar eurekaserver.jar

#execute the application
ENTRYPOINT ["java","-jar","/eurekaserver.jar"]