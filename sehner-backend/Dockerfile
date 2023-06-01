# Use the official Maven image as the base image
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /sehner-backend

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Use a separate build stage to build the application
#FROM dependencies AS build

# Copy the source code to the container
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Create a new image with the JAR file
FROM openjdk:17-jdk-slim
WORKDIR /sehner-backend
COPY --from=build /sehner-backend/target/*.jar backend.jar

# Expose the application port
EXPOSE 8090

# Start the application
CMD ["java", "-jar", "backend.jar"]
