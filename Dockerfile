FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/backend.jar backend.jar
EXPOSE 8080
CMD["java","-jar","sprinboot-farm-backend.jar"]
