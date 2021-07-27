package br.com.alura.challenge.spring.api.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String mensage) {
        super(mensage);
    }

}
