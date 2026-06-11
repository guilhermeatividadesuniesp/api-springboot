package com.example.iespflix.dto;

import java.time.Instant;
import java.util.UUID;

public class FavoritoResponse {

    private UUID usuarioId;
    private UUID conteudoId;
    private Instant criadoEm;

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getConteudoId() {
        return conteudoId;
    }

    public void setConteudoId(UUID conteudoId) {
        this.conteudoId = conteudoId;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }
}
