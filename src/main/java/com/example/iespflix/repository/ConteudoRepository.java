package com.example.iespflix.repository;

import com.example.iespflix.entity.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConteudoRepository extends JpaRepository<Conteudo, UUID> {
}
