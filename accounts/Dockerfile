FROM openjdk:17-jdk-slim as build
MAINTAINER sarvesh

COPY target/*.jar accounts.jar

#execute the application
ENTRYPOINT ["java","-jar","/accounts.jar"]