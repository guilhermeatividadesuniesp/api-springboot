package com.example.iespflix.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public class MetodoPagamentoCreateRequest {

    @NotNull
    private UUID usuarioId;

    @NotBlank
    @Size(max = 20)
    private String bandeira;

    @NotBlank
    @Size(min = 4, max = 4)
    private String ultimos4;

    @NotNull
    @Min(1)
    @Max(12)
    private Short mesExp;

    @NotNull
    @Min(2024)
    private Short anoExp;

    @NotBlank
    @Size(max = 150)
    private String nomePortador;

    @NotBlank
    @Size(max = 120)
    private String tokenGateway;

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
}
