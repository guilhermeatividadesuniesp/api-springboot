package com.example.iespflix.service;

import com.example.iespflix.dto.MetodoPagamentoCreateRequest;
import com.example.iespflix.dto.MetodoPagamentoResponse;
import com.example.iespflix.entity.MetodoPagamento;
import com.example.iespflix.entity.Usuario;
import com.example.iespflix.repository.MetodoPagamentoRepository;
import com.example.iespflix.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class MetodoPagamentoService {

    private final MetodoPagamentoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public MetodoPagamentoService(MetodoPagamentoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public MetodoPagamentoResponse create(MetodoPagamentoCreateRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        MetodoPagamento metodo = MetodoPagamento.builder()
                .usuario(usuario)
                .bandeira(request.getBandeira())
                .ultimos4(request.getUltimos4())
                .mesExp(request.getMesExp())
                .anoExp(request.getAnoExp())
                .nomePortador(request.getNomePortador())
                .tokenGateway(request.getTokenGateway())
                .build();

        return toResponse(repository.save(metodo));
    }

    public java.util.List<MetodoPagamentoResponse> listByUsuario(UUID usuarioId) {
        return repository.findByUsuario_Id(usuarioId).stream()
                .map(this::toResponse)
                .toList();
    }

    public MetodoPagamentoResponse findById(UUID id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Método de pagamento não encontrado"));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private MetodoPagamentoResponse toResponse(MetodoPagamento metodo) {
        MetodoPagamentoResponse response = new MetodoPagamentoResponse();
        response.setId(metodo.getId());
        response.setUsuarioId(metodo.getUsuario().getId());
        response.setBandeira(metodo.getBandeira());
        response.setUltimos4(metodo.getUltimos4());
        response.setMesExp(metodo.getMesExp());
        response.setAnoExp(metodo.getAnoExp());
        response.setNomePortador(metodo.getNomePortador());
        response.setTokenGateway(metodo.getTokenGateway());
        response.setCriadoEm(metodo.getCriadoEm());
        return response;
    }
}
