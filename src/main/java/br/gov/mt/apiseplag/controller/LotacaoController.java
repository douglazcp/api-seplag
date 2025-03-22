package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.model.Lotacao;
import br.gov.mt.apiseplag.service.LotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lotacao")
public class LotacaoController {
    @Autowired
    private LotacaoService lotacaoService;

    @GetMapping
    public List<Lotacao> listarTodos() {
        return lotacaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Lotacao> buscarPorId(@PathVariable Long id) {
        return lotacaoService.buscarPorId(id);
    }

    @PostMapping
    public Lotacao salvar(@RequestBody Lotacao unidade) {
        return lotacaoService.salvar(unidade);
    }

    @PutMapping
    public Lotacao atualizar(@RequestBody Lotacao unidade) {
        return lotacaoService.salvar(unidade);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        lotacaoService.deletar(id);
    }
}
