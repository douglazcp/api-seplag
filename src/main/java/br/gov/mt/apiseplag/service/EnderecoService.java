package br.gov.mt.apiseplag.service;

import br.gov.mt.apiseplag.model.Endereco;
import br.gov.mt.apiseplag.model.Unidade;
import br.gov.mt.apiseplag.repository.EnderecoRepository;
import br.gov.mt.apiseplag.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }

    public List<Endereco> listarEnderecoFuncional(String nomeServidor) {
        return enderecoRepository.findAllByPessoaEndereco_Pessoa_NomeContaining(nomeServidor);
    }
}
