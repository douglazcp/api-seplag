package br.gov.mt.apiseplag.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServidorEfetivoDto {
    private String nome;
    private int idade;
    private UnidadeDto unidade;
    private String foto;
    private PessoaDto pessoa;
}
