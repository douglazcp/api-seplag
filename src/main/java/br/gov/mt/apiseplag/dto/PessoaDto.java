package br.gov.mt.apiseplag.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaDto {
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String mae;
    private String pai;
    private FotoPessoaDto fotoPessoa;

}
