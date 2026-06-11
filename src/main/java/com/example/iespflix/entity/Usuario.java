package com.example.iespflix.entity;

import com.example.iespflix.enums.Perfil;
import com.example.iespflix.validation.CPFouCNPJ;
import com.example.iespflix.validation.ValidEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    private UUID id;

    @Column(name = "nome_completo", length = 150, nullable = false)
    @NotBlank
    @Size(max = 150)
    private String nomeCompleto;

    @Column(name = "data_nascimento", nullable = false)
    @NotNull
    private LocalDate dataNascimento;

    @Column(name = "email", length = 254, nullable = false, unique = true)
    @NotBlank
    @Size(max = 254)
    @Email
    private String email;

    @Column(name = "senha_hash", length = 60, nullable = false)
    @NotBlank
    @Size(min = 8)
    private String senhaHash;

    @Column(name = "cpf_cnpj", length = 14, unique = true)
    @CPFouCNPJ
    private String cpfCnpj;

    @Column(name = "perfil", length = 20, nullable = false)
    @NotBlank
    @ValidEnum(enumClass = Perfil.class, ignoreCase = true)
    private String perfil;

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
