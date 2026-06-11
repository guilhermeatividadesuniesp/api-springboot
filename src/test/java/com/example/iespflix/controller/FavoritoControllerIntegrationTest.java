package com.example.iespflix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FavoritoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFavorite_thenListByUser() throws Exception {
        UUID usuarioId = criarUsuario("fav-user@iespflix.local", "11144477735");
        UUID conteudoId = criarConteudo("Suspense Noturno", "SUSPENSE", "Suspense psicológico no interior.", "Suspense");

        String favoriteBody = objectMapper.writeValueAsString(new FavoritoPayload(usuarioId, conteudoId));

        mockMvc.perform(post("/api/favoritos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(favoriteBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioId").value(usuarioId.toString()))
                .andExpect(jsonPath("$.conteudoId").value(conteudoId.toString()));

        mockMvc.perform(get("/api/favoritos").param("usuarioId", usuarioId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].conteudoId").value(conteudoId.toString()));
    }

    @Test
    void deleteFavorite_removesRecord() throws Exception {
        UUID usuarioId = criarUsuario("fav-user2@iespflix.local", "01928374655");
        UUID conteudoId = criarConteudo("Doc Musical", "FILME", "Um documentário sobre música.", "Documentario");

        String favoriteBody = objectMapper.writeValueAsString(new FavoritoPayload(usuarioId, conteudoId));

        mockMvc.perform(post("/api/favoritos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(favoriteBody))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/favoritos")
                        .param("usuarioId", usuarioId.toString())
                        .param("conteudoId", conteudoId.toString()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/favoritos").param("usuarioId", usuarioId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    private UUID criarUsuario(String email, String cpfCnpj) throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                new UsuarioPayload("Usuario Favorito", "1995-05-05", email, "Senha1234", cpfCnpj, "CLIENTE")
        );

        String response = mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return UUID.fromString(objectMapper.readTree(response).get("id").asText());
    }

    private UUID criarConteudo(String titulo, String tipo, String sinopse, String genero) throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                new ConteudoPayload(titulo, tipo, (short) 2025, (short) 95, "8.30", sinopse, "https://trailer.example.com/trailer", genero)
        );

        String response = mockMvc.perform(post("/api/conteudos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return UUID.fromString(objectMapper.readTree(response).get("id").asText());
    }

    private static final class FavoritoPayload {
        public final UUID usuarioId;
        public final UUID conteudoId;

        FavoritoPayload(UUID usuarioId, UUID conteudoId) {
            this.usuarioId = usuarioId;
            this.conteudoId = conteudoId;
        }
    }

    private static final class UsuarioPayload {
        public final String nomeCompleto;
        public final String dataNascimento;
        public final String email;
        public final String senha;
        public final String cpfCnpj;
        public final String perfil;

        UsuarioPayload(String nomeCompleto, String dataNascimento, String email, String senha, String cpfCnpj, String perfil) {
            this.nomeCompleto = nomeCompleto;
            this.dataNascimento = dataNascimento;
            this.email = email;
            this.senha = senha;
            this.cpfCnpj = cpfCnpj;
            this.perfil = perfil;
        }
    }

    private static final class ConteudoPayload {
        public final String titulo;
        public final String tipo;
        public final Short ano;
        public final Short duracaoMinutos;
        public final String relevancia;
        public final String sinopse;
        public final String trailerUrl;
        public final String genero;

        ConteudoPayload(String titulo, String tipo, Short ano, Short duracaoMinutos, String relevancia, String sinopse, String trailerUrl, String genero) {
            this.titulo = titulo;
            this.tipo = tipo;
            this.ano = ano;
            this.duracaoMinutos = duracaoMinutos;
            this.relevancia = relevancia;
            this.sinopse = sinopse;
            this.trailerUrl = trailerUrl;
            this.genero = genero;
        }
    }
}
