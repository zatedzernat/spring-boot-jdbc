#
# Build stage
#
FROM maven:3.8-openjdk-11-slim AS builder

COPY src /home/app/src

COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM openjdk:11-jre-slim

COPY --from=builder /home/app/target/spring-boot-jdbc.jar /tmp/spring-boot-jdbc.jar

EXPOSE 1150

ENTRYPOINT ["java","-jar","/tmp/spring-boot-jdbc.jar"]

# Ref1: https://www.callicoder.com/spring-boot-mysql-react-docker-compose-example/
# Ref2: https://stackoverflow.com/questions/27767264/how-to-dockerize-maven-project-and-how-many-ways-to-accomplish-it