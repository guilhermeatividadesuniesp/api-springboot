package com.example.iespflix.dto;

import com.example.iespflix.validation.CPFouCNPJ;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioUpdateRequest {

    @NotBlank
    @Size(max = 150)
    private String nomeCompleto;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    @Email
    @Size(max = 254)
    private String email;

    @CPFouCNPJ
    private String cpfCnpj;

    @NotBlank
    @Size(max = 20)
    private String perfil;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
