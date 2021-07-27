package br.com.alura.challenge.spring.api.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "categorias", uniqueConstraints = { @UniqueConstraint(columnNames = "titulo") })
public class Categoria extends Padrao {

    @Column(nullable = false, unique = true, length = 100)
    private String titulo;

    @Column(nullable = false, length = 7)
    private String cor;

    @JsonIgnoreProperties(value = "categoria")
    @OneToMany(mappedBy = "categoria")
    private List<Video> videos = new ArrayList<>();

    public Categoria() {
        super();
    }

    public Categoria(String id, LocalDateTime dataCriacao, LocalDateTime dataModificacao, String titulo, String cor) {
        super(id, dataCriacao, dataModificacao);
        this.titulo = titulo;
        this.cor = cor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public List<Video> getVideos() {
        return videos;
    }

}
