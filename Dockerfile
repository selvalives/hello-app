# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container (optional but neat)
WORKDIR /app

# Copy the repackaged JAR file from target folder into the image
COPY target/hello-app-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
