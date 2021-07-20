package br.com.alura.challenge.spring.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorDto {

    @Builder.Default
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    private int status;
    private String path;
    private String error;
    private String message;

    private List<FieldError> fields;

    @Getter
    @AllArgsConstructor
    public static class FieldError {
        private String name;
        private String message;
    }

}