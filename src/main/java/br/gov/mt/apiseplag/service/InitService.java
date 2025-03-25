package br.gov.mt.apiseplag.service;

import br.gov.mt.apiseplag.model.*;
import br.gov.mt.apiseplag.repository.CidadeRepository;
import br.gov.mt.apiseplag.repository.EnderecoRepository;
import br.gov.mt.apiseplag.repository.PessoaRepository;
import br.gov.mt.apiseplag.repository.UnidadeRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InitService {

    private final UnidadeRepository unidadeRepository;
    private final CidadeRepository cidadeRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    public InitService(UnidadeRepository unidadeRepository, CidadeRepository cidadeRepository, EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.unidadeRepository = unidadeRepository;
        this.cidadeRepository = cidadeRepository;
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public void initAmbiente() throws Exception {
        iniciarUnidade();
        iniciarCidade();
        iniciarEndereco();
//        iniciarPessoa();
        iniciarPessoaEndereco();
    }

    private void iniciarUnidade() throws Exception {
        try {
            Unidade unidade = new Unidade();
            unidade.setNome("Unidade TESTE");
            unidade.setSigla("UT");
            unidadeRepository.save(unidade);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar unidade: " + e.getMessage());
        }
    }

    private void iniciarCidade() throws Exception {
        try {
            Cidade cidade = new Cidade();
            cidade.setNome("Cidade TESTE");
            cidade.setUf("CT");
            cidadeRepository.save(cidade);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar cidade: " + e.getMessage());
        }
    }

    private void iniciarEndereco() throws Exception {
        try {
            Endereco endereco = new Endereco();
            endereco.setTipoLogradouro("TipoLogradouro TESTE");
            endereco.setLogradouro("Logradouro TESTE");
            endereco.setNumero(1);
            endereco.setBairro("Bairro TESTE");
            endereco.setCidade(cidadeRepository.findByNome("Cidade TESTE"));
            enderecoRepository.save(endereco);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar endereco: " + e.getMessage());
        }
    }

    private void iniciarPessoa() throws Exception {
        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome("Pessoa TEST");
            pessoa.setDataNascimento(LocalDate.now().minusYears(20));
            pessoa.setSexo("Masculino");
            pessoa.setMae("Mae TEST");
            pessoa.setPai("Pai TEST");
            // pessoaRepository.save(pessoa); ID 22 e 23
        } catch (Exception e) {
            throw new Exception("Erro ao salvar pessoa: " + e.getMessage());
        }
    }

    private void iniciarPessoaEndereco() throws Exception {
        try {
            Pessoa pessoa = pessoaRepository.findAll().get(1);
            PessoaEndereco pessoaEndereco = new PessoaEndereco();
            pessoaEndereco.setPessoa(pessoa);
            pessoaEndereco.setEndereco(enderecoRepository.findAll().get(0));
            pessoa.setPessoaEndereco(pessoaEndereco);
            pessoaRepository.save(pessoa);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar pessoa-endereco: " + e.getMessage());
        }
    }
}
