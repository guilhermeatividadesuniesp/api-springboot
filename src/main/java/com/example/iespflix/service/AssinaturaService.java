package com.example.iespflix.service;

import com.example.iespflix.dto.AssinaturaCreateRequest;
import com.example.iespflix.dto.AssinaturaResponse;
import com.example.iespflix.entity.Assinatura;
import com.example.iespflix.entity.Plano;
import com.example.iespflix.entity.Usuario;
import com.example.iespflix.enums.StatusAssinatura;
import com.example.iespflix.repository.AssinaturaRepository;
import com.example.iespflix.repository.PlanoRepository;
import com.example.iespflix.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class AssinaturaService {

    private final AssinaturaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;

    public AssinaturaService(AssinaturaRepository repository,
                             UsuarioRepository usuarioRepository,
                             PlanoRepository planoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.planoRepository = planoRepository;
    }

    public AssinaturaResponse create(AssinaturaCreateRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Plano plano = planoRepository.findById(request.getPlanoId())
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));

        Assinatura assinatura = Assinatura.builder()
                .usuario(usuario)
                .plano(plano)
                .status(StatusAssinatura.ATIVA.name())
                .build();

        return toResponse(repository.save(assinatura));
    }

    public java.util.List<AssinaturaResponse> listByUsuario(UUID usuarioId) {
        return repository.findByUsuario_Id(usuarioId).stream()
                .map(this::toResponse)
                .toList();
    }

    public AssinaturaResponse findById(UUID id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada"));
    }

    public AssinaturaResponse cancel(UUID id) {
        Assinatura assinatura = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada"));

        assinatura.setStatus(StatusAssinatura.CANCELADA.name());
        assinatura.setCanceladaEm(Instant.now());

        return toResponse(repository.save(assinatura));
    }

    private AssinaturaResponse toResponse(Assinatura assinatura) {
        AssinaturaResponse response = new AssinaturaResponse();
        response.setId(assinatura.getId());
        response.setUsuarioId(assinatura.getUsuario().getId());
        response.setPlanoId(assinatura.getPlano().getId());
        response.setStatus(assinatura.getStatus());
        response.setIniciadaEm(assinatura.getIniciadaEm());
        response.setCanceladaEm(assinatura.getCanceladaEm());
        return response;
    }
}
