package br.com.alura.challenge.spring.api.rest;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alura.challenge.spring.api.dto.VideoDto;
import br.com.alura.challenge.spring.api.entity.Video;
import br.com.alura.challenge.spring.api.exception.EntityNotFoundException;
import br.com.alura.challenge.spring.api.service.VideoService;
import br.com.alura.challenge.spring.api.view.VideoView;

@RestController
@RequestMapping(value = "/videos", produces = "application/json")
public class VideoRest {

    @Autowired
    private VideoService service;

    @GetMapping
    public ResponseEntity<List<VideoView>> getAll() {
        List<VideoView> videos = service.findAll();
        if (videos.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(videos);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<VideoDto> create(@RequestBody @Valid VideoDto dto) {
        Video video = service.createOrUpdate(dto, Optional.ofNullable(null));
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(video.getId()).toUri())
                .body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getOne(@PathVariable String id) throws EntityNotFoundException {
        return ResponseEntity.ok(service.findOne(id));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> update(@RequestBody @Valid VideoDto dto, @PathVariable String id)
            throws EntityNotFoundException {
        service.createOrUpdate(dto, Optional.ofNullable(id));
        return ResponseEntity.ok(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) throws EntityNotFoundException {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

}