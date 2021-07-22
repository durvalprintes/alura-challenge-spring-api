package br.com.alura.challenge.spring.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Basica {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, length = 255)
    private String id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @LastModifiedDate
    private LocalDateTime dataModificacao;

    public Basica() {
    }

    public Basica(String id, LocalDateTime dataCriacao, LocalDateTime dataModificacao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

}
