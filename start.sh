#!/bin/bash

# Avvia il backend
cd backend
./mvnw spring-boot:run &

# Avvia il frontend
cd ../frontend
npm start
