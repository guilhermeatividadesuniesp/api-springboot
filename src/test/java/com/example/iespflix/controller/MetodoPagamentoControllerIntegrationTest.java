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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MetodoPagamentoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPaymentMethod_thenListByUser() throws Exception {
        UUID usuarioId = criarUsuario("pag-user@iespflix.local", "77788899900");

        String requestBody = objectMapper.writeValueAsString(
                new MetodoPagamentoPayload(usuarioId, "VISA", "1234", (short) 12, (short) 2028, "Titular Teste", "tok_abc123")
        );

        mockMvc.perform(post("/api/metodos-pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioId").value(usuarioId.toString()))
                .andExpect(jsonPath("$.ultimos4").value("1234"));

        mockMvc.perform(get("/api/metodos-pagamento").param("usuarioId", usuarioId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tokenGateway").value("tok_abc123"));
    }

    private UUID criarUsuario(String email, String cpfCnpj) throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                new UsuarioPayload("Usuario Pagamento", "1990-05-10", email, "Senha1234", cpfCnpj, "CLIENTE")
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

    private static final class MetodoPagamentoPayload {
        public final UUID usuarioId;
        public final String bandeira;
        public final String ultimos4;
        public final Short mesExp;
        public final Short anoExp;
        public final String nomePortador;
        public final String tokenGateway;

        MetodoPagamentoPayload(UUID usuarioId, String bandeira, String ultimos4, Short mesExp, Short anoExp, String nomePortador, String tokenGateway) {
            this.usuarioId = usuarioId;
            this.bandeira = bandeira;
            this.ultimos4 = ultimos4;
            this.mesExp = mesExp;
            this.anoExp = anoExp;
            this.nomePortador = nomePortador;
            this.tokenGateway = tokenGateway;
        }
    }
}
