FROM eclipse-temurin:22-jdk AS builder

WORKDIR /app
COPY . .

# Configuración para Maven en Java 24
RUN apt-get update && \
    apt-get install -y --no-install-recommends curl && \
    sed -i 's/\r$//' mvnw && \
    chmod +x mvnw

ENV MAVEN_OPTS="-Xmx512m -XX:+TieredCompilation -XX:TieredStopAtLevel=1"

# Fuerza la descarga de dependencias primero
RUN ./mvnw dependency:go-offline -B

# Build con logging detallado
RUN ./mvnw clean package -DskipTests -B -e -X

FROM eclipse-temurin:22-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]