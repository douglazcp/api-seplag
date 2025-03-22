package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.model.Cidade;
import br.gov.mt.apiseplag.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidade")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listarTodos() {
        return cidadeService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Cidade> buscarPorId(@PathVariable Long id) {
        return cidadeService.buscarPorId(id);
    }

    @PostMapping
    public Cidade salvar(@RequestBody Cidade cidade) {
        return cidadeService.salvar(cidade);
    }

    @PutMapping
    public Cidade atualizar(@RequestBody Cidade cidade) {
        return cidadeService.salvar(cidade);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        cidadeService.deletar(id);
    }
}
