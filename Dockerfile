FROM eclipse-temurin:17-jre

WORKDIR /app
COPY target/*.jar /app/app.jar
COPY . .
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./app.jar"]
