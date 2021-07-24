package br.com.alura.challenge.spring.api.projection.view;

import java.util.List;

public interface CategoriaAndVideosView extends CategoriaView {

    List<VideoWithoutCategoriaView> getVideos();

}
