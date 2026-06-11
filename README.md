# IESPFLIX Backend

API backend desenvolvida em Spring Boot para gerenciar usuários, conteúdos, favoritos, planos, assinaturas e métodos de pagamento.

## Tecnologias

- Java 17
- Spring Boot 3.2.0
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Security Crypto
- Flyway
- H2 Database
- Lombok
- JUnit 5 / Spring Boot Test

## Como executar

1. Instale o Maven localmente.
2. No diretório do projeto, execute:

```bash
mvn clean package
mvn spring-boot:run
```

Se você quiser apenas executar os testes:

```bash
mvn test
```

## Configuração padrão

O aplicativo usa H2 em memória no modo PostgreSQL para ambiente local, conforme definido em `src/main/resources/application.yml`.

## Endpoints principais

### Usuários

- `POST /api/usuarios` - criar usuário
- `PUT /api/usuarios/{id}` - atualizar usuário
- `GET /api/usuarios/{id}` - obter usuário por id
- `GET /api/usuarios` - listar usuários paginados

### Conteúdos

- `POST /api/conteudos` - criar conteúdo
- `PUT /api/conteudos/{id}` - atualizar conteúdo
- `GET /api/conteudos/{id}` - obter conteúdo por id
- `DELETE /api/conteudos/{id}` - excluir conteúdo
- `GET /api/conteudos` - pesquisar conteúdo com filtros
  - Parâmetros: `tipo`, `genero`, `q`, `page`, `size`

### Planos

- `POST /api/planos` - criar plano
- `GET /api/planos` - listar planos paginados
- `GET /api/planos/{id}` - obter plano por id
- `PUT /api/planos/{id}` - atualizar plano
- `DELETE /api/planos/{id}` - excluir plano

### Favoritos

- `POST /api/favoritos` - adicionar favorito
- `GET /api/favoritos?usuarioId={id}` - listar favoritos de um usuário
- `DELETE /api/favoritos?usuarioId={id}&conteudoId={id}` - remover favorito

### Métodos de pagamento

- `POST /api/metodos-pagamento` - criar método de pagamento
- `GET /api/metodos-pagamento?usuarioId={id}` - listar métodos por usuário
- `GET /api/metodos-pagamento/{id}` - obter método por id
- `DELETE /api/metodos-pagamento/{id}` - excluir método

### Assinaturas

- `POST /api/assinaturas` - criar assinatura
- `GET /api/assinaturas?usuarioId={id}` - listar assinaturas por usuário
- `GET /api/assinaturas/{id}` - obter assinatura por id
- `PUT /api/assinaturas/{id}/cancelar` - cancelar assinatura

## Testes

Os testes de integração e de repositório foram adicionados em `src/test/java`. Use `mvn test` para executá-los.

## Observações

- O projeto já inclui `Flyway` para migrations de banco de dados.
- A API usa validações de bean validation e um manipulador global de exceções para respostas uniformes.
