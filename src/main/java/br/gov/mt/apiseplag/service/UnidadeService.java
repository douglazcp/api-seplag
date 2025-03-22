package br.gov.mt.apiseplag.service;

import br.gov.mt.apiseplag.model.Unidade;
import br.gov.mt.apiseplag.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {
    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<Unidade> listarTodos() {
        return unidadeRepository.findAll();
    }

    public Optional<Unidade> buscarPorId(Long id) {
        return unidadeRepository.findById(id);
    }

    public Unidade salvar(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    public void deletar(Long id) {
        unidadeRepository.deleteById(id);
    }
}
