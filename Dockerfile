FROM eclipse-temurin:21-jdk-alpine AS build

ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY .mvn .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY --from=build /app/target/main-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
