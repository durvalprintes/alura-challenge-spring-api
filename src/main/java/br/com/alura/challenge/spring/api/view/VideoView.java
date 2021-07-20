package br.com.alura.challenge.spring.api.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface VideoView {

    String getId();

    String getTitulo();

    String getDescricao();

    String getUrl();

    @JsonProperty(value = "categoria")
    String getCategoriaDescricao();

}
