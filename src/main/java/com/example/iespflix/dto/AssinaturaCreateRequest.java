package com.example.iespflix.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class AssinaturaCreateRequest {

    @NotNull
    private UUID usuarioId;

    @NotNull
    private UUID planoId;

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
}
