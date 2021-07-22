package br.com.alura.challenge.spring.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorDto {

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    private int status;
    private String error;
    private String path;
    private String message;
    private List<FieldError> fields;

    public ErrorDto(int status) {
        this.status = status;
    }

    public ErrorDto(int status, String error, String path, String message, List<FieldError> fields) {
        this.status = status;
        this.error = error;
        this.path = path;
        this.message = message;
        this.fields = fields;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getFields() {
        return fields;
    }

    public void setFields(List<FieldError> fields) {
        this.fields = fields;
    }

    public static class FieldError {
        private String name;
        private String detail;

        public FieldError(String name, String message) {
            this.name = name;
            this.detail = message;
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return detail;
        }

    }

}