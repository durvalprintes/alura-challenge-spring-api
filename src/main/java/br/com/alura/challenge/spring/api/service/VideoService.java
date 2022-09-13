package br.com.alura.challenge.spring.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.spring.api.entity.Video;
import br.com.alura.challenge.spring.api.exception.ResourceNotFoundException;
import br.com.alura.challenge.spring.api.projection.dto.VideoDto;
import br.com.alura.challenge.spring.api.projection.view.VideoListView;
import br.com.alura.challenge.spring.api.projection.view.VideoView;
import br.com.alura.challenge.spring.api.repository.VideoRepository;
import br.com.alura.challenge.spring.api.util.Util;

@Service
public class VideoService {

    private static final String ERROR_NOTFOUND_VIDEO = "error.notfound.video";

    @Value("${categoria.livre}")
    private String categoriaLivre;

    @Autowired
    private VideoRepository repository;

    @Autowired
    private CategoriaService categoria;

    public Page<VideoListView> findAll(String titulo, Pageable pageable) throws ResourceNotFoundException {
        Page<VideoListView> videos = repository.findByTituloIgnoreCaseContainingOrderByTitulo(titulo, pageable);
        if (videos.isEmpty())
            throw new ResourceNotFoundException(ERROR_NOTFOUND_VIDEO);
        return videos;
    }

    public VideoView findVideo(String id) throws ResourceNotFoundException {
        return repository.getWithCategoriaById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_NOTFOUND_VIDEO));
    }

    public Video createOrUpdate(VideoDto dto, String id) {
        Video video = new Video();
        if (Optional.ofNullable(id).isPresent())
            video = findOne(id);
        BeanUtils.copyProperties(dto, video, Util.getBlankPropertyNames(dto));
        video.setCategoria(
                categoria.findOne(dto.getCategoriaId().trim().length() > 0 ? dto.getCategoriaId() : categoriaLivre));
        return repository.save(video);
    }

    public void remove(String id) throws ResourceNotFoundException {
        repository.delete(findOne(id));
    }

    private Video findOne(String id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ERROR_NOTFOUND_VIDEO));
    }

}
