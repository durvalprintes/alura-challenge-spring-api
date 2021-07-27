package br.com.alura.challenge.spring.api.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensage) {
        super(mensage);
    }

}
