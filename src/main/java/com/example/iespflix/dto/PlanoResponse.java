package com.example.iespflix.dto;

import java.util.UUID;

public class PlanoResponse {

    private UUID id;
    private String codigo;
    private Short limiteDiario;
    private Short streamsSimultaneos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
