# 1) Etapa de construcción (opcional: si quieres compilar dentro del Docker build)
FROM maven:3.9.6-eclipse-temurin-24 AS builder
WORKDIR /app
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN sed -i 's/\r$//' mvnw \
    && chmod +x mvnw \
    && ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw clean package -DskipTests

# 2) Etapa de ejecución
FROM openjdk:24-jre-slim
WORKDIR /app

# Copiamos solo el JAR que queremos ejecutar
COPY --from=builder /app/target/platform-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto de la app
EXPOSE 8080

# ENTRYPOINT en formato JSON evita problemas de parsing
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
