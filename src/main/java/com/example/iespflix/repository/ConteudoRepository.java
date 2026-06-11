package com.example.iespflix.repository;

import com.example.iespflix.entity.Conteudo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ConteudoRepository extends JpaRepository<Conteudo, UUID> {

    @Query("select c from Conteudo c " +
            "where (:tipo is null or lower(c.tipo) = lower(:tipo)) " +
            "and (:genero is null or lower(c.genero) = lower(:genero)) " +
            "and (:q is null or lower(c.titulo) like lower(concat('%', :q, '%')) " +
            "or lower(c.sinopse) like lower(concat('%', :q, '%')))")
    Page<Conteudo> search(@Param("tipo") String tipo,
                          @Param("genero") String genero,
                          @Param("q") String q,
                          Pageable pageable);
}
