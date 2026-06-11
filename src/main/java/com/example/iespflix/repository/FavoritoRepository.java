package com.example.iespflix.repository;

import com.example.iespflix.entity.Favorito;
import com.example.iespflix.entity.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {
    List<Favorito> findByUsuario_Id(UUID usuarioId);
}
