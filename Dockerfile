# Stage 1: Build the app from your code
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /home/app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]