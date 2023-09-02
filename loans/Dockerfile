FROM openjdk:17-jdk-slim as build
MAINTAINER sarvesh

COPY target/*.jar loans.jar

#execute the application
ENTRYPOINT ["java","-jar","/loans.jar"]