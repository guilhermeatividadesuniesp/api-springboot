package com.example.iespflix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "conteudo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conteudo {

    @Id
    private UUID id;

    @Column(name = "titulo", length = 200, nullable = false)
    @NotBlank
    @Size(max = 200)
    private String titulo;

    @Column(name = "tipo", length = 10, nullable = false)
    @NotBlank
    private String tipo; // FILME | SERIE

    @Column(name = "ano", nullable = false)
    @Min(1888)
    @Max(2100)
    private Short ano;

    @Column(name = "duracao_minutos", nullable = false)
    @Min(1)
    @Max(999)
    private Short duracaoMinutos;

    @Column(name = "relevancia", precision = 4, scale = 2, nullable = false)
    @NotNull
    private BigDecimal relevancia;

    @Column(name = "sinopse", columnDefinition = "TEXT")
    private String sinopse;

    @Column(name = "trailer_url", length = 500)
    private String trailerUrl;

    @Column(name = "genero", length = 50)
    private String genero;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private Instant atualizadoEm;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}
