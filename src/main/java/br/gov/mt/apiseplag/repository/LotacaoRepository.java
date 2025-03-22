package br.gov.mt.apiseplag.repository;

import br.gov.mt.apiseplag.model.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {
}
