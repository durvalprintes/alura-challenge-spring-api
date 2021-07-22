package br.com.alura.challenge.spring.api.exception;

import java.util.List;

import br.com.alura.challenge.spring.api.exception.ErrorDto.FieldError;

public class ErrorBuilder {

    private int status;
    private String path;
    private String error;
    private String message;
    private List<FieldError> fields;

    public ErrorBuilder(int status) {
        this.status = status;
    }

    public ErrorBuilder setError(String error) {
        this.error = error;
        return this;
    }

    public ErrorBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public ErrorBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorBuilder setFields(List<FieldError> fields) {
        this.fields = fields;
        return this;
    }

    public ErrorDto build() {
        return new ErrorDto(status, error, path, message, fields);
    }

}