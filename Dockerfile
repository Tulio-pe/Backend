FROM eclipse-temurin:22-jdk AS builder

WORKDIR /app
COPY . .


# Configuración mejorada del wrapper
RUN apt-get update && \
    apt-get install -y --no-install-recommends curl ca-certificates && \
    # Normaliza todos los scripts
    find . -type f -name "mvnw" -o -name "*.sh" -exec sed -i 's/\r$//' {} \; && \
    chmod +x mvnw && \
    # Verifica el ambiente
    ./mvnw --version && \
    java -version

# Build con cache optimizado
RUN ./mvnw clean package -DskipTests -B -e -Dmaven.test.skip=true

FROM eclipse-temurin:22-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]