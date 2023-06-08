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
            ProcessBuilder processBuilder = new ProcessBuilder("python", "Simulated Python Station/main.py");
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.out.println("Error during the generation of the Simulated IoT Station.");
            }
            else {
                System.out.println("Simulated IoT Station succesfully generated!");

                Runtime rt = Runtime.getRuntime();
                String url = "http://127.0.0.1:5000/";
                String[] browsers = { "google-chrome", "firefox", "mozilla", "epiphany", "konqueror",
                        "netscape", "opera", "links", "lynx" };

                StringBuffer cmd = new StringBuffer();
                for (int i = 0; i < browsers.length; i++)
                    if(i == 0)
                        cmd.append(String.format(    "%s \"%s\"", browsers[i], url));
                    else
                        cmd.append(String.format(" || %s \"%s\"", browsers[i], url));
                // If the first didn't work, try the next browser and so on

                rt.exec(new String[] { "sh", "-c", cmd.toString() });

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void merodeIoTApp() throws IOException {

        ProcessBuilder pb = new ProcessBuilder("IoT-EDG-Rest-Services/extras/start-service.bat");
        pb.redirectError();
        try {
            Process p = pb.start();
            try (InputStream inputStream = p.getInputStream()) {
                int in = -1;
                while ((in = inputStream.read()) != -1) {
                    System.out.print((char)in);
                }
            }
            System.out.println("Exited with " + p.waitFor());
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }

                System.out.println("IoT-EDG-Rest-Services succesfully started!");
                Runtime.getRuntime().exec("google-chrome-stable " + "http://localhost:4567/");
            }

}
