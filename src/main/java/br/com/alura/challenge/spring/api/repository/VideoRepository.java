package br.com.alura.challenge.spring.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.spring.api.entity.Video;
import br.com.alura.challenge.spring.api.view.VideoView;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    List<VideoView> findAllByOrderByTitulo();

}
