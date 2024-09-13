FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/myapp.jar /app/myapp.jar
CMD ["java", "-jar", "/app/myapp.jar"]
