package br.com.alura.challenge.spring.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video extends Padrao {

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String url;

    @Column(name = "thumbnail_url", nullable = true, length = 100)
    private String thumbnailUrl;

    @Column(name = "total_aprova", nullable = false, length = 100)
    private Long totalAprova = 0L;

    @Column(name = "total_rejeita", nullable = false, length = 100)
    private Long totalRejeita = 0L;

    @JsonIgnoreProperties("videos")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

}
