#
# Build stage
#
#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
# Use official base image of Java Runtime
FROM openjdk:11

# Set volume point to /tmp
VOLUME /tmp

EXPOSE 8080

# Add the application's JAR file to the container
ADD target/*.jar app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Ref1: https://www.callicoder.com/spring-boot-mysql-react-docker-compose-example/
# Ref2: https://stackoverflow.com/questions/27767264/how-to-dockerize-maven-project-and-how-many-ways-to-accomplish-it