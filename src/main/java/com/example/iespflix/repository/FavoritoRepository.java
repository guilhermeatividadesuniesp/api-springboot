package com.example.iespflix.repository;

import com.example.iespflix.entity.Favorito;
import com.example.iespflix.entity.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {
}
