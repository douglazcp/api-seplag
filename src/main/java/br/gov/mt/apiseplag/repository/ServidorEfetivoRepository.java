package br.gov.mt.apiseplag.repository;

import br.gov.mt.apiseplag.model.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {

    /*
    @Query("SELECT e FROM Endereco e WHERE e.pessoaEndereco.pessoa.nome LIKE %:nomeServidor%")
    List<Endereco> findAllByNomeServidorLike(@Param("nomeServidor") String nomeServidor);
     */
    @Query("""
    SELECT st FROM ServidorEfetivo st
    JOIN st.pessoa p
    JOIN p.pessoaEndereco pe
    JOIN pe.endereco e
    JOIN e.unidadeEndereco ue
    JOIN ue.unidade u
    WHERE u.id = :unidadeId
    """)
    List<ServidorEfetivo> findAllByUnidade(@Param("idUnidade") int idUnidade);
}
