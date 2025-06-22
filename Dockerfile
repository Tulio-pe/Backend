# ------------ BUILD (JDK 21) ------------
FROM maven:3.9.7-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:resolve          # cache dependencias
COPY src ./src
RUN mvn -q -DskipTests package          # genera target/*.jar

# ------------ RUNTIME (JRE 21 Alpine) ------------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
ENV PORT=8080
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
