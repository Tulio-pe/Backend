# ---- BUILD (JDK 24 EA) ----
FROM maven:3.9.8-openjdk-24 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:resolve
COPY src ./src
RUN mvn -q -DskipTests package          # genera target/*.jar

# ---- RUNTIME ----
FROM openjdk:24-ea-slim
WORKDIR /app
ENV PORT=8080
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
