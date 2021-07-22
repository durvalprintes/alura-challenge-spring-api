package br.com.alura.challenge.spring.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

    @NotBlank
    @Size(min = 1, max = 50)
    private String titulo;

    @NotBlank
    @Size(min = 1, max = 150)
    private String descricao;

    @NotBlank
    @URL
    @Size(min = 1, max = 100)
    private String url;

    @NotBlank
    private String categoriaId;
}
