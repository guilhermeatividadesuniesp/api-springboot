package com.example.iespflix.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "favorito")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Favorito {

    @EmbeddedId
    private FavoritoId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("conteudoId")
    @JoinColumn(name = "conteudo_id")
    private Conteudo conteudo;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;
}
