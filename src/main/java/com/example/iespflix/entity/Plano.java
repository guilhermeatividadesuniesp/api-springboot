package com.example.iespflix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "plano")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Plano {

    @Id
    private UUID id;

    @Column(name = "codigo", length = 20, nullable = false, unique = true)
    @NotBlank
    private String codigo; // BASICO, PADRAO, PREMIUM

    @Column(name = "limite_diario", nullable = false)
    private Short limiteDiario;

    @Column(name = "streams_simultaneos", nullable = false)
    private Short streamsSimultaneos;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}
