package com.example.iespflix.controller;

import com.example.iespflix.dto.AssinaturaCreateRequest;
import com.example.iespflix.dto.AssinaturaResponse;
import com.example.iespflix.service.AssinaturaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/assinaturas")
public class AssinaturaController {

    private final AssinaturaService service;

    public AssinaturaController(AssinaturaService service) {
        this.service = service;
    }

    @PostMapping
    public AssinaturaResponse create(@RequestBody @Valid AssinaturaCreateRequest request) {
        return service.create(request);
    }

    @GetMapping
    public java.util.List<AssinaturaResponse> listByUsuario(@RequestParam UUID usuarioId) {
        return service.listByUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public AssinaturaResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}/cancelar")
    public AssinaturaResponse cancel(@PathVariable UUID id) {
        return service.cancel(id);
    }
}
