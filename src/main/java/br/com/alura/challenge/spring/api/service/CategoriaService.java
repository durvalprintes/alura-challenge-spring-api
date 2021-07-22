package br.com.alura.challenge.spring.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.spring.api.dto.CategoriaDto;
import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.exception.EntityNotFoundException;
import br.com.alura.challenge.spring.api.repository.CategoriaRepository;
import br.com.alura.challenge.spring.api.view.CategoriaView;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaView> findAll() {
        return repository.findAllByOrderByDescricao();
    }

    public Categoria createOrUpdate(CategoriaDto dto, Optional<String> id) {
        Categoria categoria = new Categoria();
        if (id.isPresent())
            categoria = findOne(id.get());
        BeanUtils.copyProperties(dto, categoria);
        return repository.save(categoria);
    }

    public Categoria findOne(String id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("error.notfound.categoria"));
    }

    public Categoria findVideos(String id) {
        return repository.getVideosById(id).orElseThrow(() -> new EntityNotFoundException("error.notfound.categoria"));
    }

    public void remove(String id) {
        repository.delete(findOne(id));
    }

}
