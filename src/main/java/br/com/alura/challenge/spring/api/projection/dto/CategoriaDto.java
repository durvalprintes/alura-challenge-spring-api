package br.com.alura.challenge.spring.api.projection.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.validator.CategoriaCreateValidator;
import br.com.alura.challenge.spring.api.validator.CategoriaUpdateValidator;
import br.com.alura.challenge.spring.api.validator.Cor;
import br.com.alura.challenge.spring.api.validator.Unique;

public class CategoriaDto {

    @NotBlank(groups = CategoriaCreateValidator.class)
    @Size(min = 1, max = 100, groups = { CategoriaCreateValidator.class, CategoriaUpdateValidator.class })
    @Unique(message = "Categoria já existente.", groups = { CategoriaCreateValidator.class,
            CategoriaUpdateValidator.class })
    private String titulo;

    @NotBlank(groups = CategoriaCreateValidator.class)
    @Cor(message = "Código hexadecimal inválido. Formato aceito '#000000'.", groups = { CategoriaCreateValidator.class,
            CategoriaUpdateValidator.class })
    @Size(min = 4, max = 7, groups = { CategoriaCreateValidator.class, CategoriaUpdateValidator.class })
    private String cor;

    public CategoriaDto() {
    }

    public CategoriaDto(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    public CategoriaDto(Categoria categoria) {
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCor() {
        return cor;
    }

}
