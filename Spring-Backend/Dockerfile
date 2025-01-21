# Spring-Backend/Dockerfile
# Use an official maven image to build the application
FROM maven:3.9.9-amazoncorretto-23 as build

# Set the working directory
WORKDIR /app

# List files to debug
#RUN ls -al /app

# Copy the pom.xml and download dependencies
COPY ./pom.xml ./
RUN mvn dependency:go-offline

# List files to debug
#RUN ls -al /app

# List files to debug
#RUN ls -al /app

# Copy the rest of the application code
COPY ./src ./src

# Build the application
RUN mvn package -DskipTests

# Use an official Amazon Corretto 23 image to run the application
FROM amazoncorretto:23

# Copy the built jar file from the build stage
COPY --from=build /app/target/firewall-automation-0.0.1-SNAPSHOT.jar /app/firewall-automation.jar

# Expose port 8080
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "/app/firewall-automation.jar"]