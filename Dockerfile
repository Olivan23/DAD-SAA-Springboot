# Usar una imagen base de Maven para construir la aplicación
FROM maven:3.8.4-openjdk-17 AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y los archivos fuente al contenedor
COPY pom.xml .
COPY src ./src

# Ejecutar el comando Maven para empaquetar la aplicación
RUN mvn clean package -DskipTests

# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la fase de construcción
COPY --from=build /app/target/saa-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
