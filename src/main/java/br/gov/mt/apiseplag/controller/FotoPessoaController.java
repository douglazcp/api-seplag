package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.model.FotoPessoa;
import br.gov.mt.apiseplag.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foto-pessoa")
public class FotoPessoaController {

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
        /*
        teste upload retorno:
            5df35221-e097-4442-8060-5b32f06818d4-Edital Seletivo SEPLAG.pdf
         */
        return ResponseEntity.ok(uploadedFiles);
    }

    @PostMapping("/upload-foto-pessoa")
    public ResponseEntity<?> uploadFotoPessoa(FotoPessoa fotoPessoa) {
        fotoPessoa.setData(LocalDate.now());
        if (fotoPessoa.getFoto() == null) {
            return ResponseEntity.badRequest().body(List.of("Nenhum arquivo enviado!"));
        }
        try {
            FotoPessoa fotoPessoaAtualizada = minioService.uploadFile(fotoPessoa, fotoPessoa.getPessoa().getId());
            return ResponseEntity.ok(fotoPessoaAtualizada);
        } catch (Exception e) {
            String errorMessage = "Erro ao enviar foto: " + e.getMessage();
            return ResponseEntity.status(500).body(errorMessage);
        }
        /*
        teste upload retorno:
            5df35221-e097-4442-8060-5b32f06818d4-Edital Seletivo SEPLAG.pdf
         */
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<String> getUrlTemporaria(@PathVariable String fileName) {
        String url = minioService.gerarUrlTemporaria(fileName);
        return ResponseEntity.ok(url);
    }
}
