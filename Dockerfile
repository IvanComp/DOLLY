FROM eclipse-temurin:19-jdk

WORKDIR /app
COPY target/*.jar /app/app.jar
EXPOSE 8080

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
