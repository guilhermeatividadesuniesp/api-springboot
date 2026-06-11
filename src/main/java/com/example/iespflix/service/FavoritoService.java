package com.example.iespflix.service;

import com.example.iespflix.dto.FavoritoCreateRequest;
import com.example.iespflix.dto.FavoritoResponse;
import com.example.iespflix.entity.Conteudo;
import com.example.iespflix.entity.Favorito;
import com.example.iespflix.entity.FavoritoId;
import com.example.iespflix.entity.Usuario;
import com.example.iespflix.repository.ConteudoRepository;
import com.example.iespflix.repository.FavoritoRepository;
import com.example.iespflix.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FavoritoService {

    private final FavoritoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ConteudoRepository conteudoRepository;

    public FavoritoService(FavoritoRepository repository,
                          UsuarioRepository usuarioRepository,
                          ConteudoRepository conteudoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.conteudoRepository = conteudoRepository;
    }

    public FavoritoResponse create(FavoritoCreateRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Conteudo conteudo = conteudoRepository.findById(request.getConteudoId())
                .orElseThrow(() -> new IllegalArgumentException("Conteúdo não encontrado"));

        FavoritoId id = new FavoritoId(usuario.getId(), conteudo.getId());
        if (repository.existsById(id)) {
            throw new IllegalArgumentException("Favorito já existe");
        }

        Favorito favorito = Favorito.builder()
                .id(id)
                .usuario(usuario)
                .conteudo(conteudo)
                .build();

        return toResponse(repository.save(favorito));
    }

    public void delete(UUID usuarioId, UUID conteudoId) {
        FavoritoId id = new FavoritoId(usuarioId, conteudoId);
        repository.deleteById(id);
    }

    public List<FavoritoResponse> listByUsuario(UUID usuarioId) {
        return repository.findByUsuario_Id(usuarioId).stream()
                .map(this::toResponse)
                .toList();
    }

    private FavoritoResponse toResponse(Favorito favorito) {
        FavoritoResponse response = new FavoritoResponse();
        response.setUsuarioId(favorito.getUsuario().getId());
        response.setConteudoId(favorito.getConteudo().getId());
        response.setCriadoEm(favorito.getCriadoEm());
        return response;
    }
}
