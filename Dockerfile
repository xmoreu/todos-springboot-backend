# Usamos la imagen oficial de Maven para compilar
FROM maven:3.9.5-eclipse-temurin-20 AS build

# Carpeta de trabajo
WORKDIR /app

# Copiamos el pom y descargamos dependencias primero (para cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del proyecto
COPY src ./src

# Compilamos el proyecto y generamos el JAR
RUN mvn clean package -DskipTests

# Segunda etapa: ejecutamos el JAR
FROM eclipse-temurin:20-jdk-alpine

WORKDIR /app

# Copiamos el JAR compilado desde la etapa build
COPY --from=build /app/target/*.jar app.jar

# Exponemos puerto
EXPOSE 8080

# Comando para ejecutar el JAR
ENTRYPOINT ["java","-jar","app.jar"]

