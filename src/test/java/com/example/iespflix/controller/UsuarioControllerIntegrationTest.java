package com.example.iespflix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser_thenFetchById() throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                new UsuarioCreatePayload("Teste Usuario", "1990-01-01", "teste1@iespflix.local", "Senha123!", "52998224725", "CLIENTE")
        );

        MvcResult result = mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("teste1@iespflix.local"))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        String id = objectMapper.readTree(responseBody).get("id").asText();

        mockMvc.perform(get("/api/usuarios/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.email").value("teste1@iespflix.local"));
    }

    @Test
    void listUsers_returnsPagedResults() throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                new UsuarioCreatePayload("Teste Listagem", "1992-02-02", "teste2@iespflix.local", "Senha123!", "11144477735", "CLIENTE")
        );

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/usuarios?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].email").exists());
    }

    private static final class UsuarioCreatePayload {
        public final String nomeCompleto;
        public final String dataNascimento;
        public final String email;
        public final String senha;
        public final String cpfCnpj;
        public final String perfil;

        UsuarioCreatePayload(String nomeCompleto, String dataNascimento, String email, String senha, String cpfCnpj, String perfil) {
            this.nomeCompleto = nomeCompleto;
            this.dataNascimento = dataNascimento;
            this.email = email;
            this.senha = senha;
            this.cpfCnpj = cpfCnpj;
            this.perfil = perfil;
        }
    }
}
