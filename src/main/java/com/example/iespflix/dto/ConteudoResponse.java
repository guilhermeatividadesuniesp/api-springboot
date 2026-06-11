package com.example.iespflix.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class ConteudoResponse {

    private UUID id;
    private String titulo;
    private String tipo;
    private Short ano;
    private Short duracaoMinutos;
    private BigDecimal relevancia;
    private String sinopse;
    private String trailerUrl;
    private String genero;
    private Instant criadoEm;
    private Instant atualizadoEm;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public Short getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Short duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public BigDecimal getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(BigDecimal relevancia) {
        this.relevancia = relevancia;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
