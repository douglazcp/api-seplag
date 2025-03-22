package br.gov.mt.apiseplag.repository;

import br.gov.mt.apiseplag.model.ServidorTemporario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Long> {
}
