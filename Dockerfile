FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/cicd-app-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
