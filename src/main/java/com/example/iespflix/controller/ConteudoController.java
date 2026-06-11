package com.example.iespflix.controller;

import com.example.iespflix.dto.ConteudoCreateRequest;
import com.example.iespflix.dto.ConteudoResponse;
import com.example.iespflix.dto.ConteudoUpdateRequest;
import com.example.iespflix.service.ConteudoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/conteudos")
public class ConteudoController {

    private final ConteudoService service;

    public ConteudoController(ConteudoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ConteudoResponse> search(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String q,
            Pageable pageable) {
        return service.search(tipo, genero, q, pageable);
    }

    @GetMapping("/{id}")
    public ConteudoResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public ConteudoResponse create(@RequestBody @Valid ConteudoCreateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ConteudoResponse update(@PathVariable UUID id, @RequestBody @Valid ConteudoUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
