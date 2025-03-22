package br.gov.mt.apiseplag.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servidor_efetivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServidorEfetivo {
    @Id
    @Column(name = "se_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;

    @Column(name = "se_matricula")
    private String matricula;

}
