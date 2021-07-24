package br.com.alura.challenge.spring.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.projection.view.CategoriaAndVideosView;
import br.com.alura.challenge.spring.api.projection.view.CategoriaListView;
import br.com.alura.challenge.spring.api.projection.view.CategoriaView;

@Lazy
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

    List<CategoriaListView> findAllByOrderByDescricao();

    boolean existsByDescricaoIgnoreCase(String descricao);

    @Query("select c from Categoria c WHERE c.id = :id")
    Optional<CategoriaView> getCategoriaById(@Param("id") String id);

    @Query("select c from Categoria c LEFT JOIN FETCH c.videos WHERE c.id = :id")
    Optional<CategoriaAndVideosView> getVideosById(@Param("id") String id);

}
