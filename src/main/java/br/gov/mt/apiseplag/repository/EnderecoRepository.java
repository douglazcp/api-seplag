package br.gov.mt.apiseplag.repository;

import br.gov.mt.apiseplag.model.Endereco;
import br.gov.mt.apiseplag.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findAllByPessoaEndereco_Pessoa_NomeContaining(String pessoaEnderecoPessoaNome);
}
