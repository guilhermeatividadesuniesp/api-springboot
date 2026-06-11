package com.example.iespflix.dto;

import java.time.Instant;
import java.util.UUID;

public class MetodoPagamentoResponse {

    private UUID id;
    private UUID usuarioId;
    private String bandeira;
    private String ultimos4;
    private Short mesExp;
    private Short anoExp;
    private String nomePortador;
    private String tokenGateway;
    private Instant criadoEm;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getUltimos4() {
        return ultimos4;
    }

    public void setUltimos4(String ultimos4) {
        this.ultimos4 = ultimos4;
    }

    public Short getMesExp() {
        return mesExp;
    }

    public void setMesExp(Short mesExp) {
        this.mesExp = mesExp;
    }

    public Short getAnoExp() {
        return anoExp;
    }

    public void setAnoExp(Short anoExp) {
        this.anoExp = anoExp;
    }

    public String getNomePortador() {
        return nomePortador;
    }

    public void setNomePortador(String nomePortador) {
        this.nomePortador = nomePortador;
    }

    public String getTokenGateway() {
        return tokenGateway;
    }

    public void setTokenGateway(String tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }
}
