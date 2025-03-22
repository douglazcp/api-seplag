package br.gov.mt.apiseplag.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {
    @Id
    @Column(name = "cid_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cid_nome")
    private String nome;

    @Column(name = "cid_uf")
    private String uf;

}
