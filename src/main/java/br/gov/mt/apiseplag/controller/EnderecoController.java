package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.model.Endereco;
import br.gov.mt.apiseplag.model.ServidorEfetivo;
import br.gov.mt.apiseplag.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listarTodos() {
        return enderecoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Endereco> buscarPorId(@PathVariable Long id) {
        return enderecoService.buscarPorId(id);
    }

    @PostMapping
    public Endereco salvar(@RequestBody Endereco unidade) {
        return enderecoService.salvar(unidade);
    }

    @PutMapping
    public Endereco atualizar(@RequestBody Endereco unidade) {
        return enderecoService.salvar(unidade);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        enderecoService.deletar(id);
    }

    @GetMapping("/funcional/{nomeServidor}")
    public List<Endereco> listarEnderecoFuncional(@PathVariable("nomeServidor") String nomeServidor) {
        return enderecoService.listarEnderecoFuncional(nomeServidor);
    }
}
