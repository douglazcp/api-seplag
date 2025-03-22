package br.gov.mt.apiseplag.repository;

import br.gov.mt.apiseplag.model.Pessoa;
import br.gov.mt.apiseplag.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
