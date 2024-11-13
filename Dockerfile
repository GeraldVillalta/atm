FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

COPY . .

# Otorgar permisos de ejecución al archivo mvnw
RUN chmod +x mvnw

RUN ./mvnw package

FROM openjdk:21-jdk-slim
EXPOSE 8080

COPY --from=build /target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]