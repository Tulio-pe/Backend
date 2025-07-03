# Usa Temurin JDK 24 en Alpine (u otra variante que prefieras)
FROM eclipse-temurin:24-jdk-alpine

# Crea y entra al directorio de la app
WORKDIR /app

# Copia todo el proyecto (incluye mvnw y .mvn/)
COPY . .

# Asegura permisos de ejecución en el Maven Wrapper
RUN chmod +x mvnw

# Construye la app
RUN ./mvnw \
      -DoutputFile=target/mvn-dependency-list.log \
      -B \
      -DskipTests \
      clean dependency:list install

# Al arrancar, busca dinámicamente el JAR en target/
CMD ["sh", "-c", "java -jar target/*.jar"]
