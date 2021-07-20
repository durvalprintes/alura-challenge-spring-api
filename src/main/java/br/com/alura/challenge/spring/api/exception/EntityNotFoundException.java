package br.com.alura.challenge.spring.api.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String mensage) {
        super(mensage);
    }

}
