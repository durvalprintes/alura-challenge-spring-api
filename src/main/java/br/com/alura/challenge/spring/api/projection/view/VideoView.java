package br.com.alura.challenge.spring.api.projection.view;

public interface VideoView extends DataView {

    String getId();

    String getTitulo();

    String getDescricao();

    String getUrl();

    String getThumbnailUrl();

    Long getTotalAprova();

    Long getTotalRejeita();

    CategoriaView getCategoria();
}
