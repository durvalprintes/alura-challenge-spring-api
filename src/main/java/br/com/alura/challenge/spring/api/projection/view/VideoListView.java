package br.com.alura.challenge.spring.api.projection.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface VideoListView {

    String getId();

    String getTitulo();

    String getDescricao();

    String getUrl();

    @JsonProperty(value = "categoria")
    String getCategoriaDescricao();

}
