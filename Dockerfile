# Stage 1: Build the application using Java 25 inside Docker
FROM maven:3.9-eclipse-temurin-25-alpine AS build
WORKDIR /app

# Copy the pom.xml and download dependencies first (for faster caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy your application source code
COPY src ./src

# Compile and package the JAR file using Java 25
RUN mvn clean package -DskipTests

# Stage 2: Final lightweight image to run your application
FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app

# Copy the built JAR from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Open your Spring Boot port
EXPOSE 8080

# Execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]
