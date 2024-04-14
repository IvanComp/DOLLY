package com.example.application.controller;

import com.example.application.model.Platform;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private final List<Platform> platformInfo = new ArrayList<>();

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

        // Trova la piattaforma da rimuovere
        for (Platform platform : platformInfo) {
            if (platform.getName().equals(platformId)) { // Utilizza un identificatore univoco, non il nome
                platformToRemove = platform;
                break;
            }
        }

        // Rimuovi la piattaforma dall'elenco se è stata trovata
        if (platformToRemove != null) {
            platformInfo.remove(platformToRemove);
        }

        return platformInfo;
    }

}
