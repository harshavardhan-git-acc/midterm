# Stage 1: Build
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run (Using Eclipse Temurin instead of the deprecated openjdk image)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Ensure the JAR name matches your actual Maven output
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
