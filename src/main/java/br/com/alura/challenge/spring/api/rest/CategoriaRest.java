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

import br.com.alura.challenge.spring.api.projection.view.CategoriaView;
import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.exception.ResourceNotFoundException;
import br.com.alura.challenge.spring.api.projection.dto.CategoriaDto;
import br.com.alura.challenge.spring.api.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias", produces = "application/json")
public class CategoriaRest {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaView>> getAll() throws ResourceNotFoundException {
        List<CategoriaView> categorias = service.findAll();
        return ResponseEntity.ok(categorias);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CategoriaDto> create(@RequestBody @Valid CategoriaDto dto) {
        Categoria categoria = service.createOrUpdate(dto, Optional.ofNullable(null));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri()).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getOne(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findOne(id));
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<Categoria> getVideos(@PathVariable String id) throws ResourceNotFoundException {
        Categoria categoria = service.findVideos(id);
        return ResponseEntity.ok(categoria);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@RequestBody @Valid CategoriaDto dto, @PathVariable String id)
            throws ResourceNotFoundException {
        service.createOrUpdate(dto, Optional.ofNullable(id));
        return ResponseEntity.ok(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) throws ResourceNotFoundException {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

}