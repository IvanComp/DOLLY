package com.example.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URI;
import java.net.URISyntaxException;

import static java.awt.SystemColor.desktop;

@SpringBootApplication
@Theme(value = "merodemicroservicesbuilderforiot")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) throws IOException, InterruptedException {

        SpringApplication.run(Application.class, args);

        //Start the MERODE IoT Web App
        //merodeIoTApp();

        //Start the Python Simulated Station
        simulatedStation();

    }

    private static void simulatedStation() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("py", "Simulated Python Station/main.py");
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.out.println("Errore durante la generazione della Simulated IoT Station.");
            } else {
                System.out.println("Simulated IoT Station generata con successo!");

                String url = "http://localhost:8081";
                String chromePath = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"; // Inserisci il percorso corretto di Chrome

                ProcessBuilder browserProcessBuilder = new ProcessBuilder(chromePath, url);

                try {
                    browserProcessBuilder.start();
                } catch (IOException e) {
                    System.err.println("Errore durante l'apertura di Google Chrome: " + e.getMessage());
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void merodeIoTApp() {
        try {
            // Percorsi dei file batch
            String initDB = "IoT-EDG-Rest-Services\\extras\\init-db.bat";
            String startDBServer = "IoT-EDG-Rest-Services\\extras\\start-server.bat";
            String startService = "IoT-EDG-Rest-Services\\extras\\start-service.bat";

            // Esegui i file batch in sequenza
            runBatchFile(initDB);
            runBatchFile(startDBServer);
            runBatchFile(startService);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runBatchFile(String filePath) throws IOException, InterruptedException {
        System.out.println("Esecuzione del file batch: " + filePath);

        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", filePath);
        processBuilder.redirectErrorStream(true); // Unisce l'output standard e l'output di errore

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        // Cattura l'output del processo
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            System.out.println("Output del processo:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        System.out.println("Codice di uscita del processo: " + exitCode);

        if (exitCode != 0) {
            System.out.println("Errore durante l'esecuzione del file batch: " + filePath);
        } else {
            System.out.println("File batch eseguito con successo: " + filePath);
        }
    }

}
