package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.model.ServidorTemporario;
import br.gov.mt.apiseplag.service.ServidorTemporarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servidor-temporario")
public class ServidorTemporarioController {
    @Autowired
    private ServidorTemporarioService servidorTemporarioService;

    @GetMapping
    public List<ServidorTemporario> listarTodos() {
        return servidorTemporarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<ServidorTemporario> buscarPorId(@PathVariable Long id) {
        return servidorTemporarioService.buscarPorId(id);
    }

    @PostMapping
    public ServidorTemporario salvar(@RequestBody ServidorTemporario servidorTemporario) {
        return servidorTemporarioService.salvar(servidorTemporario);
    }

    @PutMapping
    public ServidorTemporario atualizar(@RequestBody ServidorTemporario servidorTemporario) {
        return servidorTemporarioService.salvar(servidorTemporario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servidorTemporarioService.deletar(id);
    }
}
