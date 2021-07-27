package br.com.alura.challenge.spring.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.challenge.spring.api.repository.CategoriaRepository;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public void initialize(Unique unique) {
        unique.message();
    }

    @Override
    public boolean isValid(String descricao, ConstraintValidatorContext context) {
        return !repository.existsByTituloIgnoreCase(descricao);
    }

}
