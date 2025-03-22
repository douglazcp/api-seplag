package br.gov.mt.apiseplag.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServidorTemporario {
    @Id
    @Column(name = "st_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;

    @Column(name = "st_data_admissao")
    private LocalDate dataAdmissao;

    @Column(name = "st_data_demissao")
    private LocalDate dataDemissao;

}
