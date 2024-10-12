FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /build

COPY . .

RUN ./mvnw package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /build/target/*.jar /app/category-service.jar

EXPOSE 8080

CMD ["java", "-jar", "category-service.jar"]