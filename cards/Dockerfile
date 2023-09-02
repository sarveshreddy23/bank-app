FROM openjdk:17-jdk-slim as build
MAINTAINER sarvesh

COPY target/*.jar cards.jar

#execute the application
ENTRYPOINT ["java","-jar","/cards.jar"]