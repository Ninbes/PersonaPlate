FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/personaPlate-0.0.1-SNAPSHOT.jar /app/personaplate.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/personaplate.jar"]
