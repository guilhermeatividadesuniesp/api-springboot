-- Flyway migration: initial schema
CREATE TABLE usuarios (
  id VARCHAR(36) PRIMARY KEY,
  nome_completo VARCHAR(150) NOT NULL,
  data_nascimento DATE NOT NULL,
  email VARCHAR(254) NOT NULL UNIQUE,
  senha_hash VARCHAR(60) NOT NULL,
  cpf_cnpj VARCHAR(14) UNIQUE,
  perfil VARCHAR(20) NOT NULL,
  criado_em TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  atualizado_em TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE conteudo (
  id VARCHAR(36) PRIMARY KEY,
  titulo VARCHAR(200) NOT NULL,
  tipo VARCHAR(10) NOT NULL,
  ano SMALLINT NOT NULL,
  duracao_minutos SMALLINT NOT NULL,
  relevancia DECIMAL(4,2) NOT NULL,
  sinopse TEXT,
  trailer_url VARCHAR(500),
  genero VARCHAR(50),
  criado_em TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  atualizado_em TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE plano (
  id VARCHAR(36) PRIMARY KEY,
  codigo VARCHAR(20) UNIQUE NOT NULL,
  limite_diario SMALLINT NOT NULL,
  streams_simultaneos SMALLINT NOT NULL
);

CREATE TABLE assinatura (
  id VARCHAR(36) PRIMARY KEY,
  usuario_id VARCHAR(36) REFERENCES usuarios(id),
  plano_id VARCHAR(36) REFERENCES plano(id),
  status VARCHAR(20) NOT NULL,
  iniciada_em TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  cancelada_em TIMESTAMP(3)
);

CREATE TABLE metodo_pagamento (
  id VARCHAR(36) PRIMARY KEY,
  usuario_id VARCHAR(36) REFERENCES usuarios(id) ON DELETE CASCADE,
  bandeira VARCHAR(20) NOT NULL,
  ultimos4 CHAR(4) NOT NULL,
  mes_exp SMALLINT NOT NULL,
  ano_exp SMALLINT NOT NULL,
  nome_portador VARCHAR(150) NOT NULL,
  token_gateway VARCHAR(120) NOT NULL,
  criado_em TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE favorito (
  usuario_id VARCHAR(36) NOT NULL,
  conteudo_id VARCHAR(36) NOT NULL,
  criado_em TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (usuario_id, conteudo_id),
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
  FOREIGN KEY (conteudo_id) REFERENCES conteudo(id) ON DELETE CASCADE
);
