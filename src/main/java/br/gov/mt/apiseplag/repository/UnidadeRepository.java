package br.gov.mt.apiseplag.repository;

import br.gov.mt.apiseplag.model.Unidade;
import br.gov.mt.apiseplag.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
}
