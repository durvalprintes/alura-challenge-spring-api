package br.com.alura.challenge.spring.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.exception.BusinessException;
import br.com.alura.challenge.spring.api.exception.ResourceNotFoundException;
import br.com.alura.challenge.spring.api.projection.dto.CategoriaDto;
import br.com.alura.challenge.spring.api.projection.view.CategoriaAndVideosView;
import br.com.alura.challenge.spring.api.projection.view.CategoriaListView;
import br.com.alura.challenge.spring.api.projection.view.CategoriaView;
import br.com.alura.challenge.spring.api.projection.view.VideoWithoutCategoriaView;
import br.com.alura.challenge.spring.api.repository.CategoriaRepository;
import br.com.alura.challenge.spring.api.util.Util;

@Service
public class CategoriaService {

    private static final String ERROR_NOTFOUND_CATEGORIA = "error.notfound.categoria";

    @Value("${categoria.livre}")
    private String categoriaLivre;

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private VideoService service;

    public Page<CategoriaListView> findAll(Pageable pageable) throws ResourceNotFoundException {
        Page<CategoriaListView> categorias = repository.findAllByOrderByTitulo(pageable);
        if (categorias.isEmpty())
            throw new ResourceNotFoundException(ERROR_NOTFOUND_CATEGORIA);
        return categorias;
    }

    public Categoria findOne(String id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ERROR_NOTFOUND_CATEGORIA));
    }

    public Categoria createOrUpdate(CategoriaDto dto, Optional<String> id) throws ResourceNotFoundException {
        Categoria categoria = new Categoria();
        if (id.isPresent())
            categoria = findOne(id.get());
        BeanUtils.copyProperties(dto, categoria, Util.getBlankPropertyNames(dto));
        return repository.save(categoria);
    }

    public CategoriaView findCategoria(String id) throws ResourceNotFoundException {
        return repository.getCategoriaById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_NOTFOUND_CATEGORIA));
    }

    public Page<VideoWithoutCategoriaView> findVideos(String id, Pageable pageable) throws ResourceNotFoundException {
        CategoriaAndVideosView categoria = repository.getVideosById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_NOTFOUND_CATEGORIA));
        if (categoria.getVideos().isEmpty())
            throw new ResourceNotFoundException("error.notfound.video");
        return Util.convertListToPage(categoria.getVideos(), pageable);
    }

    public void remove(String id) throws BusinessException, ResourceNotFoundException {
        if (id.equals(categoriaLivre))
            throw new BusinessException("Categoria Livre é bloqueada para exclusão.");
        service.updateVideosToCategoriaLivre(id);
        repository.delete(findOne(id));
    }
}
