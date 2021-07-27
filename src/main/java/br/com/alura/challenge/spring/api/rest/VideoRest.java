package br.com.alura.challenge.spring.api.rest;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alura.challenge.spring.api.entity.Video;
import br.com.alura.challenge.spring.api.exception.ResourceNotFoundException;
import br.com.alura.challenge.spring.api.projection.dto.VideoDto;
import br.com.alura.challenge.spring.api.projection.view.VideoListView;
import br.com.alura.challenge.spring.api.service.VideoService;
import br.com.alura.challenge.spring.api.validator.VideoCreateValidator;
import br.com.alura.challenge.spring.api.validator.VideoUpdateValidator;

@RestController
@RequestMapping(value = "/videos", produces = "application/json")
public class VideoRest {

    @Autowired
    private VideoService service;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(name = "titulo", defaultValue = "") String titulo,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "0") int page) throws ResourceNotFoundException {
        Page<VideoListView> videos = service.findAll(titulo, PageRequest.of(page, size));
        return ResponseEntity.ok(videos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> create(@RequestBody @Validated(VideoCreateValidator.class) VideoDto dto) {
        Video video = service.createOrUpdate(dto, Optional.ofNullable(null));
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(video.getId()).toUri())
                .body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findVideo(id));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Validated(VideoUpdateValidator.class) VideoDto dto,
            @PathVariable String id) throws EntityNotFoundException {

        return ResponseEntity.ok(new VideoDto(service.createOrUpdate(dto, Optional.ofNullable(id))));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) throws ResourceNotFoundException {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

}