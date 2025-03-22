package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.model.Pessoa;
import br.gov.mt.apiseplag.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarTodos() {
        return pessoaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id);
    }

    @PostMapping
    public Pessoa salvar(@RequestBody Pessoa pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @PutMapping
    public Pessoa atualizar(@RequestBody Pessoa pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
    }
}
