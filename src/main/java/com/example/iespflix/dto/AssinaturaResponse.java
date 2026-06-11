package com.example.iespflix.dto;

import java.time.Instant;
import java.util.UUID;

public class AssinaturaResponse {

    private UUID id;
    private UUID usuarioId;
    private UUID planoId;
    private String status;
    private Instant iniciadaEm;
    private Instant canceladaEm;

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

    public UUID getPlanoId() {
        return planoId;
    }

    public void setPlanoId(UUID planoId) {
        this.planoId = planoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getIniciadaEm() {
        return iniciadaEm;
    }

    public void setIniciadaEm(Instant iniciadaEm) {
        this.iniciadaEm = iniciadaEm;
    }

    public Instant getCanceladaEm() {
        return canceladaEm;
    }

    public void setCanceladaEm(Instant canceladaEm) {
        this.canceladaEm = canceladaEm;
    }
}
