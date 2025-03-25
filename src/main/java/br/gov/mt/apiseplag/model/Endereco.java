package br.gov.mt.apiseplag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @Column(name = "end_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "end_tipo_logradouro")
    private String tipoLogradouro;

    @Column(name = "end_logradouro")
    private String logradouro;

    @Column(name = "end_numero")
    private Integer numero;

    @Column(name = "end_bairro")
    private String bairro;

    @OneToOne
    private Cidade cidade;

    @OneToOne(mappedBy = "endereco")
    @JsonIgnore
    private PessoaEndereco pessoaEndereco;

    @OneToOne(mappedBy = "endereco")
    @JsonIgnore
    private UnidadeEndereco unidadeEndereco;

}
