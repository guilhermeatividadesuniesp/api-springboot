package com.example.iespflix.controller;

import com.example.iespflix.dto.MetodoPagamentoCreateRequest;
import com.example.iespflix.dto.MetodoPagamentoResponse;
import com.example.iespflix.service.MetodoPagamentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/metodos-pagamento")
public class MetodoPagamentoController {

    private final MetodoPagamentoService service;

    public MetodoPagamentoController(MetodoPagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public MetodoPagamentoResponse create(@RequestBody @Valid MetodoPagamentoCreateRequest request) {
        return service.create(request);
    }

    @GetMapping
    public java.util.List<MetodoPagamentoResponse> listByUsuario(@RequestParam UUID usuarioId) {
        return service.listByUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public MetodoPagamentoResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
