package com.example.iespflix.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ConteudoUpdateRequest {

    @NotBlank
    @Size(max = 200)
    private String titulo;

    @NotBlank
    @Size(max = 10)
    private String tipo;

    @NotNull
    @Min(1888)
    @Max(2100)
    private Short ano;

    @NotNull
    @Min(1)
    @Max(999)
    private Short duracaoMinutos;

    @NotNull
    private BigDecimal relevancia;

    private String sinopse;

    @Size(max = 500)
    private String trailerUrl;

    @Size(max = 50)
    private String genero;

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
}
