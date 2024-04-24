package com.example.application.controller;

import com.example.application.model.Platform;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private final List<Platform> platformInfo = new ArrayList<>();
    private final Path baseFolderPath = Paths.get("src/main/resources/bpmnModel");

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

    // BPMN diagrams
    @PostMapping("/save-diagram")
    public String saveDiagram(@RequestBody String xml) throws IOException {
        System.out.println("Saving diagram to: " + baseFolderPath);

        if (!Files.exists(baseFolderPath)) {
            Files.createDirectories(baseFolderPath);
        }

        Path filePath = baseFolderPath.resolve("myDiagram.bpmn");
        Files.writeString(filePath, xml);  // Save the diagram XML
        return "Diagram saved successfully at " + filePath;
    }
}
