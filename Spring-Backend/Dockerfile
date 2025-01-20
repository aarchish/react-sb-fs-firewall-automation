# Spring-Backend/Dockerfile
# Use an official maven image to build the application
FROM maven:3.8.4-openjdk-11 as build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use an official openjdk image to run the application
FROM openjdk:11-jre-slim

# Copy the built jar file from the build stage
COPY --from=build /app/target/firewall-automation-0.0.1-SNAPSHOT.jar /app/firewall-automation.jar

# Expose port 8080
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "/app/firewall-automation.jar"]