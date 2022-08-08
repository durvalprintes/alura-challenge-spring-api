insert into backend.categorias (id, titulo, cor, data_criacao, data_modificacao) values ('e70f5663-60d7-40ed-8920-8ac62f9fe701', 'LIVRE', '#FFFFFF',  now(), now());
insert into backend.categorias (id, titulo, cor, data_criacao, data_modificacao) values ('18f8c615-7e4f-4a5c-9e2e-169270e1f28a', 'ALURA', '#FF0000',  now(), now());
insert into backend.categorias (id, titulo, cor, data_criacao, data_modificacao) values ('bd9d893a-34b3-4ff0-b4fa-fe1efa721ad5', 'DESAFIOS', '#00FF00', now(), now());
insert into backend.categorias (id, titulo, cor, data_criacao, data_modificacao) values ('6344033a-d68a-4c78-9e1a-0b31c0f16cea', 'SPRING', '#0000FF', now(), now());

insert into backend.videos(id, titulo, descricao, url, categoria_id, data_criacao, data_modificacao) values ('883c06ae-6edd-4844-b15f-50302b41956a','Conheça o canal da Alura!', 'Boas-vindas, Dev! Aqui você vai acompanhar conteúdos de Tecnologia, Design e UX', 'https://www.youtube.com/watch?v=nLPssMXOExM', '18f8c615-7e4f-4a5c-9e2e-169270e1f28a', now(), now());
insert into backend.videos(id, titulo, descricao, url, categoria_id, data_criacao, data_modificacao) values ('2ec1b78b-39b0-4815-a614-06e1c52df1b9','Como começar a programar?', 'Já ouviu falar sobre programação e quer aprender? O que devo estudar? Quais são os possíveis caminhos que posso seguir?', 'https://www.youtube.com/watch?v=94yuIVdoevc', '18f8c615-7e4f-4a5c-9e2e-169270e1f28a', now(), now());
insert into backend.videos(id, titulo, descricao, url, categoria_id, data_criacao, data_modificacao) values ('ee40f4de-0294-4161-a8cb-a4bea70ae321','O que é Spring Boot e quais as vantagens?', 'Sabia que o Spring Boot pode te deixar muito mais produtivo?', 'https://www.youtube.com/watch?v=ABjV1bObFW8', '6344033a-d68a-4c78-9e1a-0b31c0f16cea', now(), now());
