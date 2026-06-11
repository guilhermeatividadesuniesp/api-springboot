package com.example.iespflix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssinaturaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createSubscription_thenListByUser_thenCancel() throws Exception {
        UUID usuarioId = criarUsuario("assinatura-user@iespflix.local", "33322211100");
        UUID planoId = criarPlano("PADRAO", (short) 5, (short) 2);

        String requestBody = objectMapper.writeValueAsString(new AssinaturaPayload(usuarioId, planoId));

        String response = mockMvc.perform(post("/api/assinaturas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ATIVA"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        UUID assinaturaId = UUID.fromString(objectMapper.readTree(response).get("id").asText());

        mockMvc.perform(get("/api/assinaturas").param("usuarioId", usuarioId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("ATIVA"));

        mockMvc.perform(put("/api/assinaturas/{id}/cancelar", assinaturaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELADA"));
    }

    private UUID criarUsuario(String email, String cpfCnpj) throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                new UsuarioPayload("Usuario Assinatura", "1991-07-07", email, "Senha1234", cpfCnpj, "CLIENTE")
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

    private UUID criarPlano(String codigo, Short limiteDiario, Short streamsSimultaneos) throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                new PlanoPayload(codigo, limiteDiario, streamsSimultaneos)
        );

        String response = mockMvc.perform(post("/api/planos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return UUID.fromString(objectMapper.readTree(response).get("id").asText());
    }

    private static final class AssinaturaPayload {
        public final UUID usuarioId;
        public final UUID planoId;

        AssinaturaPayload(UUID usuarioId, UUID planoId) {
            this.usuarioId = usuarioId;
            this.planoId = planoId;
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

    private static final class PlanoPayload {
        public final String codigo;
        public final Short limiteDiario;
        public final Short streamsSimultaneos;

        PlanoPayload(String codigo, Short limiteDiario, Short streamsSimultaneos) {
            this.codigo = codigo;
            this.limiteDiario = limiteDiario;
            this.streamsSimultaneos = streamsSimultaneos;
        }
    }
}
