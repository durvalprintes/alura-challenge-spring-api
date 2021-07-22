package br.com.alura.challenge.spring.api.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NamedEntityGraph(name = "CategoriaWithoutVideos", attributeNodes = { @NamedAttributeNode(value = "id"),
        @NamedAttributeNode(value = "descricao"), @NamedAttributeNode(value = "dataCriacao"),
        @NamedAttributeNode(value = "dataModificacao"),

})
@Table(name = "categorias", uniqueConstraints = { @UniqueConstraint(columnNames = "descricao") })
public class Categoria extends Basica {

    @Column(nullable = false, unique = true, length = 100)
    private String descricao;

    @JsonIgnoreProperties(value = "categoria")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    public Categoria(String id, LocalDateTime dataCriacao, LocalDateTime dataModificacao, String descricao) {
        super(id, dataCriacao, dataModificacao);
        this.descricao = descricao;
    }

    public Categoria() {
        super();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
