package br.com.alura.challenge.spring.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.spring.api.projection.view.CategoriaView;
import br.com.alura.challenge.spring.api.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

    List<CategoriaView> findAllByOrderByDescricao();

    boolean existsByDescricaoIgnoreCase(String descricao);

    @EntityGraph(attributePaths = { "id", "descricao" })
    @Query("select c from Categoria c WHERE c.id = :id")
    Optional<Categoria> findGraphById(@Param("id") String id);

    @Query("select c from Categoria c LEFT JOIN FETCH c.videos WHERE c.id = :id")
    Optional<Categoria> getVideosById(@Param("id") String id);

}
