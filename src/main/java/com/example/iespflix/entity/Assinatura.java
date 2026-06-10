package com.example.iespflix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "assinatura")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Assinatura {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

    @Column(name = "status", length = 20, nullable = false)
    private String status; // ATIVA | EM_ATRASO | CANCELADA

    @CreationTimestamp
    @Column(name = "iniciada_em", nullable = false, updatable = false)
    private Instant iniciadaEm;

    @Column(name = "cancelada_em")
    private Instant canceladaEm;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}
