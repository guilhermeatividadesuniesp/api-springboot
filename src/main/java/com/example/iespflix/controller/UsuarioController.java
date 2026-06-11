package com.example.iespflix.controller;

import com.example.iespflix.dto.UsuarioCreateRequest;
import com.example.iespflix.dto.UsuarioResponse;
import com.example.iespflix.dto.UsuarioUpdateRequest;
import com.example.iespflix.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public UsuarioResponse create(@RequestBody @Valid UsuarioCreateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public UsuarioResponse update(@PathVariable UUID id, @RequestBody @Valid UsuarioUpdateRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    public UsuarioResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping
    public Page<UsuarioResponse> list(Pageable pageable) {
        return service.list(pageable);
    }
}
