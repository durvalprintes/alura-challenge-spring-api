package br.com.alura.challenge.spring.api.rest;

import java.util.Optional;

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

import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.exception.ResourceNotFoundException;
import br.com.alura.challenge.spring.api.projection.dto.CategoriaDto;
import br.com.alura.challenge.spring.api.projection.view.CategoriaListView;
import br.com.alura.challenge.spring.api.service.CategoriaService;
import br.com.alura.challenge.spring.api.validator.CategoriaCreateValidator;
import br.com.alura.challenge.spring.api.validator.CategoriaUpdateValidator;

@RestController
@RequestMapping(value = "/categorias", produces = "application/json")
public class CategoriaRest {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "0") int page) throws ResourceNotFoundException {
        Page<CategoriaListView> categorias = service.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(categorias);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Validated(CategoriaCreateValidator.class) CategoriaDto dto)
            throws ResourceNotFoundException {
        Categoria categoria = service.createOrUpdate(dto, Optional.ofNullable(null));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri()).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findCategoria(id));
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<Object> getVideos(@PathVariable String id,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "0") int page) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findVideos(id, PageRequest.of(page, size)));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Validated(CategoriaUpdateValidator.class) CategoriaDto dto,
            @PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(new CategoriaDto(service.createOrUpdate(dto, Optional.ofNullable(id))));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) throws ResourceNotFoundException {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

}