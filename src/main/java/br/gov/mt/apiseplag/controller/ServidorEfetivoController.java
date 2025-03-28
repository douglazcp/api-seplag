package br.gov.mt.apiseplag.controller;

import br.gov.mt.apiseplag.model.FotoPessoa;
import br.gov.mt.apiseplag.model.ServidorEfetivo;
import br.gov.mt.apiseplag.dto.ServidorEfetivoDto;
import br.gov.mt.apiseplag.service.FotoPessoaService;
import br.gov.mt.apiseplag.service.MinioService;
import br.gov.mt.apiseplag.service.PessoaService;
import br.gov.mt.apiseplag.service.ServidorEfetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servidor-efetivo")
public class ServidorEfetivoController {
    @Autowired
    private ServidorEfetivoService servidorEfetivoService;
    @Autowired
    private MinioService minioService;
    @Autowired
    private FotoPessoaService fotoPessoaService;
    @Autowired
    private PessoaService pessoaService;

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
    public Optional<ServidorEfetivo> buscarPorId(@PathVariable Long id) throws Exception {
        Optional<ServidorEfetivo> servidorEfetivo = servidorEfetivoService.buscarPorId(id);
        (servidorEfetivo.get()).getPessoa().getFotoPessoa().setFotos(minioService.listarArquivosPorUsuario((servidorEfetivo.get()).getPessoa().getId().toString()));

        return servidorEfetivo;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ServidorEfetivo servidorEfetivo) throws Exception {
        try {
            // Garante que a pessoa não tenha uma data de nascimento nula
            if (servidorEfetivo.getPessoa().getDataNascimento() == null) {
                servidorEfetivo.getPessoa().setDataNascimento(LocalDate.now());
            }

            // Verifica se a pessoa já está salva
//            if (servidorEfetivo.getPessoa().getId() == null) {
//                // Se a pessoa não tiver um ID (não estiver salva), salva ela antes
//                Pessoa pessoaSalva = pessoaService.salvar(servidorEfetivo.getPessoa());
//                servidorEfetivo.setPessoa(pessoaSalva);
//            }

            // Salva o servidor no banco de dados (Agora com a pessoa persistida)
            ServidorEfetivo servidorEfetivoSalvo = servidorEfetivoService.salvar(servidorEfetivo);

            // Verifica se há uma foto para upload
            if (servidorEfetivo.getPessoa().getFotoPessoa() != null &&
                    servidorEfetivo.getPessoa().getFotoPessoa().getFoto() != null) {

                // Realiza o upload da foto no MinIO
                FotoPessoa fotoPessoaSalvaComArquivo = minioService.uploadFile(servidorEfetivo.getPessoa().getFotoPessoa(), servidorEfetivo.getPessoa().getId());

                // A foto já foi salva, agora associa à pessoa
                fotoPessoaSalvaComArquivo.setPessoa(servidorEfetivo.getPessoa());

                // Salva a FotoPessoa, agora que a Pessoa está salva
                fotoPessoaService.salvar(fotoPessoaSalvaComArquivo);
            }

            // Atualiza o ServidorEfetivo com a FotoPessoa
            servidorEfetivoSalvo.setPessoa(servidorEfetivo.getPessoa());
            servidorEfetivoService.salvar(servidorEfetivoSalvo); // Atualiza com o nome do arquivo

            return ResponseEntity.ok(servidorEfetivoSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar servidor efetivo: " + e.getMessage());
        }
    }

    @PutMapping
    public ServidorEfetivo atualizar(@RequestBody ServidorEfetivo servidorEfetivo) throws Exception {
        return servidorEfetivoService.salvar(servidorEfetivo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servidorEfetivoService.deletar(id);
    }
}
