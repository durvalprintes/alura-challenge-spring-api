# Spring Boot API

API RESTful com Spring Boot, resultado do Desafio da Alura com foco em tecnologias para Back-end.

## Pré-requisitos

1. Conhecimento em Java, Spring Boot e suas dependências;
2. Instalar o _docker_ e o _docker-compose_ no seu sistema operacional;

## Execução

1. Build do projeto:

```bash
.\mvnw clean package
```

2. ~~Copie o artefato **spring-api.jar** para o diretório **/docker**~~;
3. No diretório **/docker**:

```bash
docker-compose build
```

e

```bash
docker-compose up
```

4. Caso queira remover os contêineres:

```bash
docker-compose down
```

## Especificações em Desenvolvimento

- [x] Conexão com o banco de dados Postgres;
- [x] Duas entidades com relacionamento bidirecional;
- [x] Endpoints com CRUD completo;
- [x] Tratamento de Exceções;
- [x] Validações na entrada de dados;
- [x] Documentação;
- [x] Conteinerização;
- [x] Versionamento da Base da Dados;
- [ ] Refatoração dos testes;
- [ ] Autenticação e autorização;
- [ ] Deploy;
- [ ] Gerenciamento e monitoramento;

## Recursos utilizados

- Documentação com o [Swagger](https://swagger.io/);
- Conteinerização com o [Docker](https://www.docker.com/);
- Versionamento da BD com o [Flyway](https://flywaydb.org/);
- Ambiente de testes com o [Testcontainers](https://www.testcontainers.org/);
- Deploy com [AWS](https://aws.amazon.com/pt/);
