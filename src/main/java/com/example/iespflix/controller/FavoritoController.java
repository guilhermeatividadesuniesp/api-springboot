package com.example.iespflix.controller;

import com.example.iespflix.dto.FavoritoCreateRequest;
import com.example.iespflix.dto.FavoritoResponse;
import com.example.iespflix.service.FavoritoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService service;

    public FavoritoController(FavoritoService service) {
        this.service = service;
    }

    @PostMapping
    public FavoritoResponse create(@RequestBody @Valid FavoritoCreateRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<FavoritoResponse> listByUsuario(@RequestParam UUID usuarioId) {
        return service.listByUsuario(usuarioId);
    }

    @DeleteMapping
    public void delete(@RequestParam UUID usuarioId, @RequestParam UUID conteudoId) {
        service.delete(usuarioId, conteudoId);
    }
}
