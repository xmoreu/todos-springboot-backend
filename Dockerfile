# Usa la imagen base de Java 25
FROM eclipse-temurin:25-jdk-alpine

# Directorio de la app dentro del contenedor
WORKDIR /app

# Copia el JAR desde tu target
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto (igual que en application.properties)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]
