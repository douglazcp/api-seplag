package br.gov.mt.apiseplag.service;

import br.gov.mt.apiseplag.model.Cidade;
import br.gov.mt.apiseplag.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> listarTodos() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> buscarPorId(Long id) {
        return cidadeRepository.findById(id);
    }

    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public void deletar(Long id) {
        cidadeRepository.deleteById(id);
    }
}
