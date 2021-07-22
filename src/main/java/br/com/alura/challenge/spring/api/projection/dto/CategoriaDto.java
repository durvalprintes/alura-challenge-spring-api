package br.com.alura.challenge.spring.api.projection.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.challenge.spring.api.validator.Unique;

public class CategoriaDto {

    @NotBlank
    @Size(min = 1, max = 100)
    @Unique(message = "Categoria já existente.")
    private String descricao;

    public CategoriaDto() {
    }

    public CategoriaDto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
