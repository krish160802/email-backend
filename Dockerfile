FROM almalinux:latest
RUN mkdir -p /opt/java/openjdk
ENV JAVA_HOME=/opt/java/openjdk
COPY --from=eclipse-temurin:17.0.10_7-jdk $JAVA_HOME $JAVA_HOME
ENV PATH="${JAVA_HOME}/bin:${PATH}"
COPY ./target/*.jar app.jar
ENV TZ=Asia/Kolkata
EXPOSE 8080
CMD ["java","-jar","app.jar"]