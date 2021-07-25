# Spring Boot API

API RESTful com Spring Boot, resultado do Desafio da Alura com foco em tecnologias para Back-end.

## Pré-requisitos

1. Conhecimento em Java, Spring Boot e suas dependências;
2. Instalar o *docker* e o *docker-compose* no seu sistema operacional;

## Execução

1. Build do projeto:
```bash
.\mvnw clean package
```
2. Copie o artefato **spring-api.jar** para o diretorio **/src/main/docker**;
3. No diretório acima:
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
- [x] Duas Entidades com relacionamento bidirecional;
- [x] Endpoints com CRUD básico completo;
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
