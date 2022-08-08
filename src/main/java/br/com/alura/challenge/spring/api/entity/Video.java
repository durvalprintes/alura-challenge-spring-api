package br.com.alura.challenge.spring.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "videos")
public class Video extends Padrao {

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String url;

    @Column(name = "thumbnail_url", nullable = true, length = 100)
    private String thumbnailUrl;

    @JsonIgnoreProperties("videos")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    public Video(String id, LocalDateTime dataCriacao, LocalDateTime dataModificacao, String titulo, String descricao,
            String url, String thumbnailUrl, Categoria categoria) {
        super(id, dataCriacao, dataModificacao);
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.categoria = categoria;
    }

    public Video() {
        super();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
