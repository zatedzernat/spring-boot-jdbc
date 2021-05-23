# Use official base image of Java Runtime
FROM openjdk:11

# Set volume point to /tmp
VOLUME /tmp

EXPOSE 8080

# Add the application's JAR file to the container
ADD target/*.jar app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]

# https://www.callicoder.com/spring-boot-mysql-react-docker-compose-example/