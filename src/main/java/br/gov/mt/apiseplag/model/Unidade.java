package br.gov.mt.apiseplag.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "unidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Unidade {
    @Id
    @Column(name = "unid_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unid_nome")
    private String nome;

    @Column(name = "unid_sigla")
    private String sigla;

}
