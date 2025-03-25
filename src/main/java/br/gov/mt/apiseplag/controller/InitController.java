package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/montar-ambiente")
public class InitController {

    @Autowired
    private InitService initService;

    @GetMapping("/init")
    public ResponseEntity<?> initAmbiente() {
        try {
            initService.initAmbiente();
            return ResponseEntity.ok("Ambiente iniciado e carregado com sucesso");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar servidor efetivo: " + e.getMessage());
        }
    }

}
