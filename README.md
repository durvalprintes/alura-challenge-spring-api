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

2. ~~Copie o artefato **spring-api.jar** para o diretorio **/docker**~~;
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

- [x] Conexao com o banco de dados Postgres;
- [x] Duas entidades com relacionamento bidirecional;
- [x] Endpoints com CRUD completo;
- [x] Tratamento de Exceções;
- [x] Validações na entrada de dados;
- [x] Documentação;
- [x] Conteinerização;
- [ ] Testes;
- [ ] Autenticação e autorização;
- [ ] Deploy;
- [ ] Gerenciamento e monitoramento;

## Recursos utilizados

- Documentação com o [Swagger](https://swagger.io/);
- Conteinerização com o [Docker](https://www.docker.com/);
- Deploy com [Heroku](https://www.heroku.com/);
