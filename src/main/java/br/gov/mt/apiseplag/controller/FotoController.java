package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fotos")
public class FotoController {

    @Autowired
    private MinioService minioService;

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFotos(@RequestParam("files") MultipartFile[] files) {
        if (files.length == 0) {
            return ResponseEntity.badRequest().body(List.of("Nenhum arquivo enviado!"));
        }

        List<String> uploadedFiles = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                String filename = minioService.uploadFile(file);
                uploadedFiles.add(filename);
            } catch (Exception e) {
                String errorMessage = "Erro ao enviar " + file.getOriginalFilename() + ": " + e.getMessage();
                errors.add(errorMessage);
            }
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(500).body(errors);
        }

        return ResponseEntity.ok(uploadedFiles);
    }
}
