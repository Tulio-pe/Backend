# 1) Stage de compilación con Maven (o Gradle)
FROM maven:3.9.4-openjdk-24 AS build
WORKDIR /src
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests -B

# 2) Stage final solo con JRE
FROM eclipse-temurin:24-jre-jammy
WORKDIR /app

# Configura Kestrel-style (Spring Boot u otro) para puerto 8080
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"
EXPOSE 8080

# Copia solo el .jar optimizado
COPY --from=build /src/target/platform-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
