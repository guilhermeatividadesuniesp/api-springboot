package com.example.iespflix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConteudoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createContent_thenSearchByQuery() throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                new ConteudoCreatePayload("Aventura Espacial", "FILME", (short) 2024, (short) 120, "8.50", "Uma jornada pelos astros.", "https://trailer.example.com", "Aventura")
        );

        mockMvc.perform(post("/api/conteudos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Aventura Espacial"));

        mockMvc.perform(get("/api/conteudos")
                        .param("q", "espacial")
                        .param("tipo", "FILME")
                        .param("genero", "Aventura")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].titulo").value("Aventura Espacial"));
    }

    private static final class ConteudoCreatePayload {
        public final String titulo;
        public final String tipo;
        public final Short ano;
        public final Short duracaoMinutos;
        public final String relevancia;
        public final String sinopse;
        public final String trailerUrl;
        public final String genero;

        ConteudoCreatePayload(String titulo, String tipo, Short ano, Short duracaoMinutos, String relevancia, String sinopse, String trailerUrl, String genero) {
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
