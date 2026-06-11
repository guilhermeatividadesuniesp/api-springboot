package com.example.iespflix.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class FavoritoCreateRequest {

    @NotNull
    private UUID usuarioId;

    @NotNull
    private UUID conteudoId;

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
}
