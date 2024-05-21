FROM eclipse-temurin:19-jdk

# Installazione di Maven
#RUN apt-get update && apt-get install -y maven && apt-get clean

WORKDIR /app
COPY target/*.jar /app/app.jar

# Copia gli script aggiuntivi necessari
# COPY IoT-EDG-Rest-Services /app/IoT-EDG-Rest-Services
#RUN apt-get update && apt-get install -y dos2unix && apt-get clean && \
    #dos2unix /app/IoT-EDG-Rest-Services/extras/*.sh && \
    #chmod +x /app/IoT-EDG-Rest-Services/extras/*.sh

EXPOSE 8080

# Esegui gli script necessari e poi avvia l'applicazione principale
#ENTRYPOINT ["/bin/bash", "-c", "/app/IoT-EDG-Rest-Services/extras/build-application.sh && /app/IoT-EDG-Rest-Services/extras/start-db-server.sh && /app/IoT-EDG-Rest-Services/extras/init-db.sh && /app/IoT-EDG-Rest-Services/extras/start-service.sh && java -jar /app/app.jar"]
ENTRYPOINT ["java", "-jar", "/app/app.jar"]