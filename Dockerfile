FROM eclipse-temurin:24-jdk AS builder
WORKDIR /app
COPY . .
RUN apt-get update \
 && apt-get install -y --no-install-recommends curl \
 && sed -i 's/\r$//' mvnw \
 && chmod +x mvnw

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:24-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]