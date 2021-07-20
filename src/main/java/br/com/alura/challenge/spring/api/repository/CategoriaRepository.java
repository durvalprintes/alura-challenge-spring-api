package br.com.alura.challenge.spring.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.view.CategoriaView;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

    List<CategoriaView> findAllByOrderByDescricao();

    boolean existsByDescricaoIgnoreCase(String descricao);

}
