package com.example.iespflix.service;

import com.example.iespflix.dto.ConteudoCreateRequest;
import com.example.iespflix.dto.ConteudoResponse;
import com.example.iespflix.dto.ConteudoUpdateRequest;
import com.example.iespflix.entity.Conteudo;
import com.example.iespflix.repository.ConteudoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Transactional
public class ConteudoService {

    private final ConteudoRepository repository;

    public ConteudoService(ConteudoRepository repository) {
        this.repository = repository;
    }

    public Page<ConteudoResponse> search(String tipo, String genero, String q, Pageable pageable) {
        tipo = StringUtils.hasText(tipo) ? tipo : null;
        genero = StringUtils.hasText(genero) ? genero : null;
        q = StringUtils.hasText(q) ? q : null;
        return repository.search(tipo, genero, q, pageable).map(this::toResponse);
    }

    public ConteudoResponse findById(UUID id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Conteúdo não encontrado"));
    }

    public ConteudoResponse create(ConteudoCreateRequest request) {
        Conteudo conteudo = Conteudo.builder()
                .titulo(request.getTitulo())
                .tipo(request.getTipo())
                .ano(request.getAno())
                .duracaoMinutos(request.getDuracaoMinutos())
                .relevancia(request.getRelevancia())
                .sinopse(request.getSinopse())
                .trailerUrl(request.getTrailerUrl())
                .genero(request.getGenero())
                .build();
        return toResponse(repository.save(conteudo));
    }

    public ConteudoResponse update(UUID id, ConteudoUpdateRequest request) {
        Conteudo conteudo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conteúdo não encontrado"));

        conteudo.setTitulo(request.getTitulo());
        conteudo.setTipo(request.getTipo());
        conteudo.setAno(request.getAno());
        conteudo.setDuracaoMinutos(request.getDuracaoMinutos());
        conteudo.setRelevancia(request.getRelevancia());
        conteudo.setSinopse(request.getSinopse());
        conteudo.setTrailerUrl(request.getTrailerUrl());
        conteudo.setGenero(request.getGenero());

        return toResponse(repository.save(conteudo));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private ConteudoResponse toResponse(Conteudo conteudo) {
        ConteudoResponse response = new ConteudoResponse();
        response.setId(conteudo.getId());
        response.setTitulo(conteudo.getTitulo());
        response.setTipo(conteudo.getTipo());
        response.setAno(conteudo.getAno());
        response.setDuracaoMinutos(conteudo.getDuracaoMinutos());
        response.setRelevancia(conteudo.getRelevancia());
        response.setSinopse(conteudo.getSinopse());
        response.setTrailerUrl(conteudo.getTrailerUrl());
        response.setGenero(conteudo.getGenero());
        response.setCriadoEm(conteudo.getCriadoEm());
        response.setAtualizadoEm(conteudo.getAtualizadoEm());
        return response;
    }
}
