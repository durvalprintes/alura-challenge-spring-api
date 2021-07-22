package br.com.alura.challenge.spring.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.spring.api.dto.VideoDto;
import br.com.alura.challenge.spring.api.entity.Video;
import br.com.alura.challenge.spring.api.exception.ResourceNotFoundException;
import br.com.alura.challenge.spring.api.repository.VideoRepository;
import br.com.alura.challenge.spring.api.view.VideoView;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private CategoriaService service;

    public List<VideoView> findAll() throws ResourceNotFoundException {
        List<VideoView> videos = repository.findAllByOrderByTitulo();
        if (videos.isEmpty())
            throw new ResourceNotFoundException("error.notfound.video");
        return videos;
    }

    public Video findOne(String id) throws ResourceNotFoundException {
        return repository.getWithCategoriaById(id)
                .orElseThrow(() -> new ResourceNotFoundException("error.notfound.video"));
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

}
