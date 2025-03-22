package br.gov.mt.apiseplag.model.dto;

import br.gov.mt.apiseplag.model.ServidorEfetivo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
public class ServidorEfetivoDto {
    private String nome;
    private int idade;
    private UnidadeDto unidade;
    private String foto;
}
