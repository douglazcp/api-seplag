package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.model.Endereco;
import br.gov.mt.apiseplag.model.ServidorEfetivo;
import br.gov.mt.apiseplag.model.dto.ServidorEfetivoDto;
import br.gov.mt.apiseplag.model.dto.UnidadeDto;
import br.gov.mt.apiseplag.service.ServidorEfetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servidor-efetivo")
public class ServidorEfetivoController {
    @Autowired
    private ServidorEfetivoService servidorEfetivoService;

    @GetMapping
    public List<ServidorEfetivo> listarTodos(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return servidorEfetivoService.listarTodos(pageRequest);
    }

    @GetMapping("/listar-por-id-unidade/{id}")
    public List<ServidorEfetivoDto> listarPorIdUnidade(@PathVariable String id) {
        List<ServidorEfetivo> servidoresEfetivos = servidorEfetivoService.listarPorIdUnidade(Integer.parseInt(id));
        return servidorEfetivoService.mapearServidorEfetivoEmServidorEfetivoDto(servidoresEfetivos);
    }

    @GetMapping("/{id}")
    public Optional<ServidorEfetivo> buscarPorId(@PathVariable Long id) {
        return servidorEfetivoService.buscarPorId(id);
    }

    @PostMapping
    public ServidorEfetivo salvar(@RequestBody ServidorEfetivo servidorEfetivo) {
        return servidorEfetivoService.salvar(servidorEfetivo);
    }

    @PutMapping
    public ServidorEfetivo atualizar(@RequestBody ServidorEfetivo servidorEfetivo) {
        return servidorEfetivoService.salvar(servidorEfetivo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servidorEfetivoService.deletar(id);
    }
}
