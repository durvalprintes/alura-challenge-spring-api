package br.com.alura.challenge.spring.api.rest;

import java.util.List;

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

import br.com.alura.challenge.spring.api.dto.CategoriaDto;
import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.exception.EntityNotFoundException;
import br.com.alura.challenge.spring.api.service.CategoriaService;
import br.com.alura.challenge.spring.api.view.CategoriaView;

@RestController
@RequestMapping(value = "/categorias", produces = "application/json")
public class CategoriaRest {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaView>> getAll() {
        List<CategoriaView> categorias = service.findAll();
        if (categorias.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categorias);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> create(@RequestBody @Valid CategoriaDto dto) {
        Categoria categoria = service.createOrUpdate(Categoria.builder().descricao(dto.getDescricao()).build());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri()).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getOne(@PathVariable String id) throws EntityNotFoundException {
        return ResponseEntity.ok(service.findOne(id));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@RequestBody @Valid CategoriaDto dto, @PathVariable String id)
            throws EntityNotFoundException {
        service.createOrUpdate(Categoria.builder().id(id).descricao(dto.getDescricao()).build());
        return ResponseEntity.ok(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) throws EntityNotFoundException {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

}