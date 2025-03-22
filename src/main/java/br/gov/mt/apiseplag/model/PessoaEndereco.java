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
public class PessoaEndereco {
    @Id
    @Column(name = "pes_end_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pes_id", unique = true)
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(name = "end_id", unique = true)
    private Endereco endereco;
}
