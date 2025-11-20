# Etapa 1: compilación
FROM maven:3.9.5-eclipse-temurin AS build
WORKDIR /app

# Copiamos pom.xml e instalamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Compilamos y generamos JAR
RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:20-jre
WORKDIR /app

# Copiamos el JAR desde la etapa build
COPY --from=build /app/target/*.jar app.jar

# Exponemos puerto
EXPOSE 8080

# Ejecutamos la app
ENTRYPOINT ["java","-jar","app.jar"]
