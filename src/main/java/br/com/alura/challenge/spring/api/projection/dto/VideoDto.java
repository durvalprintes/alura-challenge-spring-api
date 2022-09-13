package br.com.alura.challenge.spring.api.projection.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import br.com.alura.challenge.spring.api.entity.Video;
import br.com.alura.challenge.spring.api.validator.VideoCreateValidator;
import br.com.alura.challenge.spring.api.validator.VideoUpdateValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @NotBlank(groups = VideoCreateValidator.class)
    @URL(groups = { VideoCreateValidator.class, VideoUpdateValidator.class })
    @Size(min = 1, max = 100, groups = { VideoCreateValidator.class, VideoUpdateValidator.class })
    private String thumbnailUrl;

    private Long totalAprova;

    private Long totalRejeita;

    private String categoriaId;

    public VideoDto(Video video) {
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.thumbnailUrl = video.getThumbnailUrl();
        this.totalAprova = video.getTotalAprova();
        this.totalRejeita = video.getTotalRejeita();
        this.categoriaId = video.getCategoria().getId();
    }
}
