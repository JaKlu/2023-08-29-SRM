FROM eclipse-temurin:17.0.9_9-jdk-jammy
MAINTAINER jakubk.ovh
COPY target/shooting-range-management-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
