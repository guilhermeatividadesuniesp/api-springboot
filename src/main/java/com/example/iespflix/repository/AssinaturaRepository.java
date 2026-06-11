package com.example.iespflix.repository;

import com.example.iespflix.entity.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {
    List<Assinatura> findByUsuario_Id(UUID usuarioId);
}
