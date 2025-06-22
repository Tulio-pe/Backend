# -------------------------------
#  Tallerazo – Dockerfile (JDK 24)
#  Multi-stage: build → runtime
# -------------------------------

###############
# 1) Build stage
###############
FROM maven:3.9.7-eclipse-temurin-24 AS build
WORKDIR /app

# Copiamos POM y resolvemos dependencias primero (mejora cache)
COPY pom.xml .
RUN mvn -q dependency:resolve

# Copiamos el código fuente y compilamos el fat-jar Spring Boot
COPY src ./src
RUN mvn -q -DskipTests package         # genera target/*.jar

################
# 2) Runtime stage
################
FROM eclipse-temurin:24-jre-alpine
WORKDIR /app

# Puerto en Railway (o 8080 local)
ENV PORT=8080
EXPOSE 8080

# Copiamos el jar compilado
COPY --from=build /app/target/*.jar app.jar

# Arrancamos la app; usa PORT que inyecta Railway
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
