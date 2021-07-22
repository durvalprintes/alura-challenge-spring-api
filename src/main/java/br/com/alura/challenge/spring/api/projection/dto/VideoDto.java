package br.com.alura.challenge.spring.api.projection.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

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

    public VideoDto() {
    }

    public VideoDto(String titulo, String descricao, String url, String categoriaId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoriaId = categoriaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    public String getCategoriaId() {
        return categoriaId;
    }
}
