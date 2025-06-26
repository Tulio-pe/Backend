FROM openjdk:24-jdk
EXPOSE 8080
COPY target/platform-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar