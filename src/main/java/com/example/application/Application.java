package com.example.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.awt.Desktop;
import java.io.InputStream;
import java.net.URI;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static java.awt.SystemColor.desktop;

@SpringBootApplication
@Theme(value = "merodemicroservicesbuilderforiot")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(Application.class, args);

        //Start the Python Simulated Station
        simulatedStation();

        //Start the MERODE IoT Web App
        merodeIoTApp();

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
                String chromePath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"; // Inserisci il percorso corretto di Chrome

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

    private static void merodeIoTApp() throws IOException {

        // Creare ProcessBuilder per ogni file .bat
        ProcessBuilder pb1 = new ProcessBuilder("IoT-EDG-Rest-Services/extras/build-application.bat");
        ProcessBuilder pb2 = new ProcessBuilder("IoT-EDG-Rest-Services/extras/start-db-server.bat");
        ProcessBuilder pb3 = new ProcessBuilder("IoT-EDG-Rest-Services/extras/init-db.bat");
        ProcessBuilder pb4 = new ProcessBuilder("IoT-EDG-Rest-Services/extras/start-service.bat");

        // Eseguire il primo processo e attendere che termini
        executeAndPrint(pb1);

        // Eseguire il secondo processo e attendere che termini
        executeAndPrint(pb2);

        // Eseguire il terzo processo e attendere che termini
        executeAndPrint(pb3);

        // Eseguire il quarto processo e attendere che termini
        executeAndPrint(pb4);

        // Alla fine
        System.out.println("IoT-EDG-Rest-Services successfully started!");
        Runtime.getRuntime().exec("google-chrome-stable " + "http://localhost:4567/");
    }

    private static void executeAndPrint(ProcessBuilder pb) {
        try {
            Process p = pb.start();
            try (InputStream inputStream = p.getInputStream()) {
                int in;
                while ((in = inputStream.read()) != -1) {
                    System.out.print((char) in);
                }
            }
            System.out.println("Exited with " + p.waitFor());
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }


}
