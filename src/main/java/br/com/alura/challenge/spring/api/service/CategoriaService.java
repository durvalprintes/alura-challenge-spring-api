package br.com.alura.challenge.spring.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Categoria createOrUpdate(Categoria categoria) {
        if (Optional.ofNullable(categoria.getId()).isPresent() && !repository.existsById(categoria.getId()))
            throw new EntityNotFoundException("error.notfound.categoria");
        return repository.save(categoria);
    }

    public Categoria findOne(String id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("error.notfound.categoria"));
    }

    public void remove(String id) {
        repository.delete(findOne(id));
    }

}
