# Backend Dockerfile

# Use a base image with JDK 22 (since you are using JDK 22)
FROM openjdk:22-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY fullStackApplication-0.0.1-SNAPSHOT.jar /app/fullStackApplication.jar
# Expose the application port (default for Spring Boot is 8080)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "fullStackApplication.jar"]
