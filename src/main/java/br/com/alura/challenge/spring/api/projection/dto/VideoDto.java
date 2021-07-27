package br.com.alura.challenge.spring.api.projection.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import br.com.alura.challenge.spring.api.entity.Video;
import br.com.alura.challenge.spring.api.validator.VideoCreateValidator;
import br.com.alura.challenge.spring.api.validator.VideoUpdateValidator;

public class VideoDto {

    @NotBlank(groups = VideoCreateValidator.class)
    @Size(min = 1, max = 50, groups = { VideoCreateValidator.class, VideoUpdateValidator.class })
    private String titulo;

    @NotBlank(groups = VideoCreateValidator.class)
    @Size(min = 1, max = 150, groups = { VideoCreateValidator.class, VideoUpdateValidator.class })
    private String descricao;

    @NotBlank(groups = VideoCreateValidator.class)
    @URL(groups = { VideoCreateValidator.class, VideoUpdateValidator.class })
    @Size(min = 1, max = 100, groups = { VideoCreateValidator.class, VideoUpdateValidator.class })
    private String url;

    private String categoriaId = "e70f5663-60d7-40ed-8920-8ac62f9fe701";

    public VideoDto() {
    }

    public VideoDto(String titulo, String descricao, String url, String categoriaId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoriaId = categoriaId;
    }

    public VideoDto(Video video) {
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoriaId = video.getCategoria().getId();
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
