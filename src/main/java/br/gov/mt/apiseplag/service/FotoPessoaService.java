package br.gov.mt.apiseplag.service;

import br.gov.mt.apiseplag.model.FotoPessoa;
import br.gov.mt.apiseplag.repository.FotoPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class FotoPessoaService {
    @Autowired
    private FotoPessoaRepository fotoPessoaRepository;

    @Autowired
    private MinioService minioService;

    public List<FotoPessoa> listarTodos() {
        return fotoPessoaRepository.findAll();
    }

    public Optional<FotoPessoa> buscarPorId(Long id) {
        return fotoPessoaRepository.findById(id);
    }

    public FotoPessoa salvar(FotoPessoa fotoPessoa) {
        MultipartFile multipartFile = null;
        return fotoPessoaRepository.save(fotoPessoa);
    }

    public void deletar(Long id) {
        fotoPessoaRepository.deleteById(id);
    }
}
