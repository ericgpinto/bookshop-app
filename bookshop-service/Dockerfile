FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN apt-get update && apt-get install -y netcat-openbsd && apt-get clean

ENTRYPOINT ["/wait-for-it.sh", "db", "5432", "--", "java", "-jar", "app.jar"]

EXPOSE 8080
