package br.com.alura.challenge.spring.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.spring.api.entity.Video;
import br.com.alura.challenge.spring.api.exception.ResourceNotFoundException;
import br.com.alura.challenge.spring.api.projection.dto.VideoDto;
import br.com.alura.challenge.spring.api.projection.view.VideoListView;
import br.com.alura.challenge.spring.api.projection.view.VideoView;
import br.com.alura.challenge.spring.api.repository.VideoRepository;

@Service
public class VideoService {

    private static final String ERROR_NOTFOUND_VIDEO = "error.notfound.video";

    @Autowired
    private VideoRepository repository;

    @Autowired
    private CategoriaService service;

    public List<VideoListView> findAll() throws ResourceNotFoundException {
        List<VideoListView> videos = repository.findAllByOrderByTitulo();
        if (videos.isEmpty())
            throw new ResourceNotFoundException(ERROR_NOTFOUND_VIDEO);
        return videos;
    }

    public VideoView findVideo(String id) throws ResourceNotFoundException {
        return repository.getWithCategoriaById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_NOTFOUND_VIDEO));
    }

    public Video createOrUpdate(VideoDto dto, Optional<String> id) {
        Video video = new Video();
        if (id.isPresent())
            video = findOne(id.get());
        BeanUtils.copyProperties(dto, video);
        video.setCategoria(service.findOne(dto.getCategoriaId()));
        return repository.save(video);
    }

    public void remove(String id) {
        repository.delete(findOne(id));
    }

    private Video findOne(String id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ERROR_NOTFOUND_VIDEO));
    }
}
