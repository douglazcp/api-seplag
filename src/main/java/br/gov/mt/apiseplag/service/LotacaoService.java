package br.gov.mt.apiseplag.service;

import br.gov.mt.apiseplag.model.Lotacao;
import br.gov.mt.apiseplag.repository.LotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotacaoService {
    @Autowired
    private LotacaoRepository lotacaoRepository;

    public List<Lotacao> listarTodos() {
        return lotacaoRepository.findAll();
    }

    public Optional<Lotacao> buscarPorId(Long id) {
        return lotacaoRepository.findById(id);
    }

    public Lotacao salvar(Lotacao unidade) {
        return lotacaoRepository.save(unidade);
    }

    public void deletar(Long id) {
        lotacaoRepository.deleteById(id);
    }
}
