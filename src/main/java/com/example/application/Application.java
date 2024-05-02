package com.example.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.*;

@SpringBootApplication
@Theme(value = "ADAPTIVE-SHADOW")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) throws IOException, InterruptedException {

        SpringApplication.run(Application.class, args);

        //Start the Python Simulated Station
        //simulatedStation();

    }

    private static void simulatedStation() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("py", "Simulated Python Station/main.py");
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.out.println("Errore durante la generazione della Simulated IoT Sttion.");
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

}
