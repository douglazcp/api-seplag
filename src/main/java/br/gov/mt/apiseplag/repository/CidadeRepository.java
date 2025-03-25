package br.gov.mt.apiseplag.repository;

import br.gov.mt.apiseplag.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Cidade findByNome(String cidadeTeste);
}
