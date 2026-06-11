package com.example.iespflix.service;

import com.example.iespflix.dto.PlanoCreateRequest;
import com.example.iespflix.dto.PlanoResponse;
import com.example.iespflix.entity.Plano;
import com.example.iespflix.repository.PlanoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class PlanoService {

    private final PlanoRepository repository;

    public PlanoService(PlanoRepository repository) {
        this.repository = repository;
    }

    public PlanoResponse create(PlanoCreateRequest request) {
        if (repository.findByCodigo(request.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Plano já cadastrado com este código");
        }

        Plano plano = Plano.builder()
                .codigo(request.getCodigo())
                .limiteDiario(request.getLimiteDiario())
                .streamsSimultaneos(request.getStreamsSimultaneos())
                .build();

        return toResponse(repository.save(plano));
    }

    public Page<PlanoResponse> list(org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public PlanoResponse findById(UUID id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));
    }

    public PlanoResponse update(UUID id, PlanoCreateRequest request) {
        Plano plano = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));

        plano.setCodigo(request.getCodigo());
        plano.setLimiteDiario(request.getLimiteDiario());
        plano.setStreamsSimultaneos(request.getStreamsSimultaneos());

        return toResponse(repository.save(plano));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private PlanoResponse toResponse(Plano plano) {
        PlanoResponse response = new PlanoResponse();
        response.setId(plano.getId());
        response.setCodigo(plano.getCodigo());
        response.setLimiteDiario(plano.getLimiteDiario());
        response.setStreamsSimultaneos(plano.getStreamsSimultaneos());
        return response;
    }
}
