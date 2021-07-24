package br.com.alura.challenge.spring.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.spring.api.projection.view.VideoListView;
import br.com.alura.challenge.spring.api.projection.view.VideoView;
import br.com.alura.challenge.spring.api.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    List<VideoListView> findAllByOrderByTitulo();

    @Query("SELECT v FROM Video v JOIN FETCH v.categoria WHERE v.id = :id")
    Optional<VideoView> getWithCategoriaById(@Param("id") String id);

}
