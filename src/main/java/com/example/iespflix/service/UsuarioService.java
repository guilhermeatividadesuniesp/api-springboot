package com.example.iespflix.service;

import com.example.iespflix.dto.UsuarioCreateRequest;
import com.example.iespflix.dto.UsuarioResponse;
import com.example.iespflix.dto.UsuarioUpdateRequest;
import com.example.iespflix.entity.Usuario;
import com.example.iespflix.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UsuarioResponse> list(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public UsuarioResponse findById(UUID id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    public UsuarioResponse create(UsuarioCreateRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nomeCompleto(request.getNomeCompleto())
                .dataNascimento(request.getDataNascimento())
                .email(request.getEmail())
                .senhaHash(passwordEncoder.encode(request.getSenha()))
                .cpfCnpj(request.getCpfCnpj())
                .perfil(request.getPerfil())
                .build();

        return toResponse(repository.save(usuario));
    }

    public UsuarioResponse update(UUID id, UsuarioUpdateRequest request) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuario.setNomeCompleto(request.getNomeCompleto());
        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setEmail(request.getEmail());
        usuario.setCpfCnpj(request.getCpfCnpj());
        usuario.setPerfil(request.getPerfil());

        return toResponse(repository.save(usuario));
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNomeCompleto(usuario.getNomeCompleto());
        response.setDataNascimento(usuario.getDataNascimento());
        response.setEmail(usuario.getEmail());
        response.setCpfCnpj(usuario.getCpfCnpj());
        response.setPerfil(usuario.getPerfil());
        response.setCriadoEm(usuario.getCriadoEm());
        response.setAtualizadoEm(usuario.getAtualizadoEm());
        return response;
    }
}
