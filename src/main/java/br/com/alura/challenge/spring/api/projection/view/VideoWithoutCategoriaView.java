package br.com.alura.challenge.spring.api.projection.view;

public interface VideoWithoutCategoriaView {

    String getId();

    String getTitulo();

    String getDescricao();

    String getUrl();

    String getThumbnailUrl();

    Long getTotalAprova();

    Long getTotalRejeita();

}
