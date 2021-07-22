package br.com.alura.challenge.spring.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.spring.api.projection.view.CategoriaView;
import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.exception.ResourceNotFoundException;
import br.com.alura.challenge.spring.api.projection.dto.CategoriaDto;
import br.com.alura.challenge.spring.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private static final String ERROR_NOTFOUND_CATEGORIA = "error.notfound.categoria";

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaView> findAll() {
        List<CategoriaView> categorias = repository.findAllByOrderByDescricao();
        if (categorias.isEmpty())
            throw new ResourceNotFoundException(ERROR_NOTFOUND_CATEGORIA);
        return categorias;
    }

    public Categoria createOrUpdate(CategoriaDto dto, Optional<String> id) {
        Categoria categoria = new Categoria();
        if (id.isPresent())
            categoria = findOne(id.get());
        BeanUtils.copyProperties(dto, categoria);
        return repository.save(categoria);
    }

    public Categoria findOne(String id) throws ResourceNotFoundException {
        return repository.findGraphById(id).orElseThrow(() -> new ResourceNotFoundException(ERROR_NOTFOUND_CATEGORIA));
    }

    public Categoria findVideos(String id) throws ResourceNotFoundException {
        Categoria categoria = repository.getVideosById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_NOTFOUND_CATEGORIA));
        if (categoria.getVideos().isEmpty())
            throw new ResourceNotFoundException("error.notfound.video");
        return categoria;
    }

    public void remove(String id) {
        repository.delete(findOne(id));
    }

}
