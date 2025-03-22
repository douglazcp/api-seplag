package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.model.Unidade;
import br.gov.mt.apiseplag.service.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {
    @Autowired
    private UnidadeService unidadeService;

    @GetMapping
    public List<Unidade> listarTodos() {
        return unidadeService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Unidade> buscarPorId(@PathVariable Long id) {
        return unidadeService.buscarPorId(id);
    }

    @PostMapping
    public Unidade salvar(@RequestBody Unidade unidade) {
        return unidadeService.salvar(unidade);
    }

    @PutMapping
    public Unidade atualizar(@RequestBody Unidade unidade) {
        return unidadeService.salvar(unidade);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        unidadeService.deletar(id);
    }
}
