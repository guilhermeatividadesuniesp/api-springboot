package com.example.iespflix.repository;

import com.example.iespflix.entity.Conteudo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ConteudoRepositoryTest {

    @Autowired
    private ConteudoRepository repository;

    @Test
    void search_byTipoGeneroAndQuery_shouldReturnMatches() {
        Conteudo c1 = Conteudo.builder()
                .titulo("Aventura Espacial")
                .tipo("FILME")
                .ano((short) 2024)
                .duracaoMinutos((short) 120)
                .relevancia(new BigDecimal("8.50"))
                .sinopse("Uma grande aventura na galáxia.")
                .genero("Aventura")
                .build();

        Conteudo c2 = Conteudo.builder()
                .titulo("Comédia Romântica")
                .tipo("FILME")
                .ano((short) 2023)
                .duracaoMinutos((short) 100)
                .relevancia(new BigDecimal("7.20"))
                .sinopse("Uma história de amor engraçada.")
                .genero("Romance")
                .build();

        repository.save(c1);
        repository.save(c2);

        Page<Conteudo> page = repository.search("FILME", "Aventura", "Espacial", PageRequest.of(0, 10));

        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getContent()).extracting("titulo").containsExactly("Aventura Espacial");
    }
}
