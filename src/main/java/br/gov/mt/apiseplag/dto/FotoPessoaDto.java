package br.gov.mt.apiseplag.dto;

import br.gov.mt.apiseplag.model.FotoPessoa;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FotoPessoaDto {
    private LocalDate data;
    private String bucket;
    private String hash;
}
