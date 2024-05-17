FROM eclipse-temurin:17-jre

WORKDIR /app
COPY target/*.jar /app/app.jar
COPY . .

# Make sure your scripts are executable
RUN chmod +x ADAPTIVE-SHADOW/IoT-EDG-Rest-Services/extras/*.sh

# Expose port
EXPOSE 8080

# Run the scripts and then the Java application
ENTRYPOINT ["sh", "-c", "ADAPTIVE-SHADOW/IoT-EDG-Rest-Services/extras/start-db-server.sh && ADAPTIVE-SHADOW/IoT-EDG-Rest-Services/extras/init-db.sh && ADAPTIVE-SHADOW/IoT-EDG-Rest-Services/extras/start-service.sh && java -jar ./app.jar"]
