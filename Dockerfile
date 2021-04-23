FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/ship-finder-0.0.1.jar
COPY ${JAR_FILE} ship-finder-0.0.1.jar
ENTRYPOINT ["java","-jar","/ship-finder-0.0.1.jar"]