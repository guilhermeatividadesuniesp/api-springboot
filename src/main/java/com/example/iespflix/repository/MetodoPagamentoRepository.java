package com.example.iespflix.repository;

import com.example.iespflix.entity.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, UUID> {
}
