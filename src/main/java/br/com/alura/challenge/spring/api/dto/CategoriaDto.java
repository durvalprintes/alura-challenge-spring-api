package br.com.alura.challenge.spring.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.challenge.spring.api.validator.Unique;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {

    @NotBlank
    @Size(min = 1, max = 100)
    @Unique(message = "Categoria já existente.")
    private String descricao;

}
