package com.example.iespflix.controller;

import com.example.iespflix.dto.PlanoCreateRequest;
import com.example.iespflix.dto.PlanoResponse;
import com.example.iespflix.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {

    private final PlanoService service;

    public PlanoController(PlanoService service) {
        this.service = service;
    }

    @PostMapping
    public PlanoResponse create(@RequestBody @Valid PlanoCreateRequest request) {
        return service.create(request);
    }

    @GetMapping
    public org.springframework.data.domain.Page<PlanoResponse> list(org.springframework.data.domain.Pageable pageable) {
        return service.list(pageable);
    }

    @GetMapping("/{id}")
    public PlanoResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public PlanoResponse update(@PathVariable UUID id, @RequestBody @Valid PlanoCreateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
