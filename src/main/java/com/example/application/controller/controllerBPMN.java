package com.example.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class controllerBPMN {

    private final Path baseFolderPath = Paths.get("src/main/resources/bpmnModel");

    @GetMapping("/list-diagrams")
    public List<String> listDiagrams() throws IOException {
        if (!Files.exists(baseFolderPath)) {
            Files.createDirectories(baseFolderPath);
        }

        try (Stream<Path> stream = Files.list(baseFolderPath)) {
            return stream
                    .filter(file -> file.toString().endsWith(".bpmn"))
                    .map(Path::getFileName)
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }
    }

    @GetMapping("/get-diagram/{filename}")
    public ResponseEntity<String> getDiagram(@PathVariable String filename) {
        Path filePath = Paths.get("src/main/resources/bpmnModel", filename);
        if (Files.exists(filePath)) {
            try {
                String xmlContent = new String(Files.readAllBytes(filePath));
                return ResponseEntity.ok(xmlContent);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-diagram/{filename}")
    public ResponseEntity<Void> deleteDiagram(@PathVariable String filename) {
        Path filePath = Paths.get("src/main/resources/bpmnModel", filename);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save-diagram")
    public String saveDiagram(@RequestBody Map<String, String> diagramInfo) throws IOException {
        String xml = diagramInfo.get("xml");
        String filename = diagramInfo.get("filename");

        if (!Files.exists(baseFolderPath)) {
            Files.createDirectories(baseFolderPath);
        }

        Path filePath = baseFolderPath.resolve(filename + ".bpmn");
        filePath = ensureUniqueFilename(filePath);

        Files.writeString(filePath, xml);
        return "Diagram saved successfully at " + filePath;
    }

    private Path ensureUniqueFilename(Path originalPath) {
        int counter = 1;
        Path filePath = originalPath;

        while (Files.exists(filePath)) {
            String fileWithCounter = String.format("%s (%d)%s",
                    removeExtension(originalPath.getFileName().toString()),
                    counter++,
                    getFileExtension(originalPath.getFileName().toString()));
            filePath = originalPath.getParent().resolve(fileWithCounter);
        }
        return filePath;
    }

    private String removeExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot == -1) return fileName;
        return fileName.substring(0, lastDot);
    }

    private String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot == -1) return "";
        return fileName.substring(lastDot);
    }
}
