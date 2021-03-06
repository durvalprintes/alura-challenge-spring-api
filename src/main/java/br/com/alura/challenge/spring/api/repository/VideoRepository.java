package br.com.alura.challenge.spring.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.spring.api.entity.Video;
import br.com.alura.challenge.spring.api.projection.view.VideoListView;
import br.com.alura.challenge.spring.api.projection.view.VideoView;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    Page<VideoListView> findAllByOrderByTitulo(Pageable pageable);

    @Query("SELECT v FROM Video v JOIN FETCH v.categoria WHERE v.id = :id")
    Optional<VideoView> getWithCategoriaById(@Param("id") String id);

    Page<VideoListView> findByTituloIgnoreCaseContainingOrderByTitulo(String titulo, Pageable pageable);

    List<Video> findByCategoriaId(String id);

}
