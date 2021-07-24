package br.com.alura.challenge.spring.api.projection.view;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface DataView {

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    LocalDateTime getDataCriacao();

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    LocalDateTime getDataModificacao();

}
