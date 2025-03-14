# 🌟 Stage 1: Build the Spring Boot Application
FROM maven:3.9.2-eclipse-temurin-21-alpine AS builder

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml .  
COPY src/ ./src  

# Ensure static files (React build) are included
RUN mkdir -p src/main/resources/static
COPY src/main/resources/static/ src/main/resources/static/

# Build the Spring Boot application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# 🌟 Stage 2: Create a lightweight image to run the built JAR
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/coupon-distribution.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
