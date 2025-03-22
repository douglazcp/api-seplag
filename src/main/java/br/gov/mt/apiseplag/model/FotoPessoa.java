package br.gov.mt.apiseplag.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FotoPessoa {
    @Id
    @Column(name = "fp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;

    @Column(name = "fp_data")
    private LocalDate data;

    @Column(name = "fp_bucket")
    private String bucket;

    @Column(name = "fp_hash")
    private String hash;

}
