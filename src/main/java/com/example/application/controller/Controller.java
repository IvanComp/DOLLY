package com.example.application.controller;

import com.example.application.model.Platform;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    private final List<Platform> platformInfo = new ArrayList<>();


    @GetMapping("/run-scripts")
    public String runScripts() {
        try {

            // Esegue il primo script
            Process p12 = Runtime.getRuntime().exec("cmd /c start C:\\Users\\User\\Desktop\\Software\\ADAPTIVE-SHADOW\\IoT-EDG-Rest-Services\\extras\\build-application");
            p12.waitFor(); // Aspetta la fine dell'esecuzione
            Thread.sleep(15000);
            // Esegue il primo script
            Process p11 = Runtime.getRuntime().exec("cmd /c start C:\\Users\\User\\Desktop\\Software\\ADAPTIVE-SHADOW\\IoT-EDG-Rest-Services\\extras\\init-db");
            p11.waitFor(); // Aspetta la fine dell'esecuzione

            // Aspetta 5 secondi
            Thread.sleep(5000);

            Process p1 = Runtime.getRuntime().exec("cmd /c start C:\\Users\\User\\Desktop\\Software\\ADAPTIVE-SHADOW\\IoT-EDG-Rest-Services\\extras\\start-db-server");
            p1.waitFor(); // Aspetta la fine dell'esecuzione

            // Aspetta 5 secondi
            Thread.sleep(5000);

            // Esegue il secondo script
            Process p2 = Runtime.getRuntime().exec("cmd /c start C:\\Users\\User\\Desktop\\Software\\ADAPTIVE-SHADOW\\IoT-EDG-Rest-Services\\extras\\start-service");
            p2.waitFor(); // Aspetta la fine dell'esecuzione

            return "Scripts executed successfully";
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Failed to execute scripts";
        }
    }

    @PostMapping("/platform/get")
    public List<Platform> getPlatforms() throws IOException, InterruptedException {
        return platformInfo;
    }

    @PostMapping("/platform/mecrplatform")
    public List<Platform> createPlatform(@RequestBody String[] data) throws IOException, InterruptedException {
        Platform newPlatform = new Platform(data[0], data[1]);
        platformInfo.add(newPlatform);
        return platformInfo;
    }

    @DeleteMapping("/platform/meendplatform")
    public List<Platform> endPlatform(@RequestBody String[] data) throws IOException, InterruptedException {
        String platformId = data[0];
        Platform platformToRemove = null;

        for (Platform platform : platformInfo) {
            if (platform.getName().equals(platformId)) {
                platformToRemove = platform;
                break;
            }
        }

        if (platformToRemove != null) {
            platformInfo.remove(platformToRemove);
        }

        return platformInfo;
    }


}
