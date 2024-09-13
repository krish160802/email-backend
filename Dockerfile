# Stage 1: Build the JAR file
FROM eclipse-temurin:17.0.10_7-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
FROM almalinux:latest
RUN mkdir -p /opt/java/openjdk
ENV JAVA_HOME=/opt/java/openjdk
COPY --from=eclipse-temurin:17.0.10_7-jdk $JAVA_HOME $JAVA_HOME
ENV PATH="${JAVA_HOME}/bin:${PATH}"
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV TZ=Asia/Kolkata
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
