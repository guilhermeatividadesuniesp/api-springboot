package com.example.iespflix.repository;

import com.example.iespflix.entity.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, UUID> {
    List<MetodoPagamento> findByUsuario_Id(UUID usuarioId);
}
