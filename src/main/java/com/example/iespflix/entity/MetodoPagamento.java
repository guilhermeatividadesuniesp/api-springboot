package com.example.iespflix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "metodo_pagamento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MetodoPagamento {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "bandeira", length = 20, nullable = false)
    private String bandeira;

    @Column(name = "ultimos4", length = 4, nullable = false)
    private String ultimos4;

    @Column(name = "mes_exp", nullable = false)
    private Short mesExp;

    @Column(name = "ano_exp", nullable = false)
    private Short anoExp;

    @Column(name = "nome_portador", length = 150, nullable = false)
    private String nomePortador;

    @Column(name = "token_gateway", length = 120, nullable = false)
    private String tokenGateway;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}
