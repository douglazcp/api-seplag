package br.gov.mt.apiseplag.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoa_endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeEndereco {
    @Id
    @Column(name = "unid_end_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "unid_id", unique = true)
    private Unidade unidade;

    @OneToOne
    @JoinColumn(name = "end_id", unique = true)
    private Endereco endereco;
}
