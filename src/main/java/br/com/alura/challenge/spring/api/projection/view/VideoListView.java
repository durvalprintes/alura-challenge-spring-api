package br.com.alura.challenge.spring.api.projection.view;

public interface VideoListView {

    String getId();

    String getTitulo();

    String getDescricao();

    String getUrl();

    String getThumbnailUrl();

    Long getTotalAprova();

    Long getTotalRejeita();

    String getCategoriaId();

}
