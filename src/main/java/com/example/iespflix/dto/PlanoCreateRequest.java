package com.example.iespflix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PlanoCreateRequest {

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotNull
    private Short limiteDiario;

    @NotNull
    private Short streamsSimultaneos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Short getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(Short limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    public Short getStreamsSimultaneos() {
        return streamsSimultaneos;
    }

    public void setStreamsSimultaneos(Short streamsSimultaneos) {
        this.streamsSimultaneos = streamsSimultaneos;
    }
}
