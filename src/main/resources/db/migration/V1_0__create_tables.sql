CREATE TABLE IF NOT EXISTS backend.categorias (
	id VARCHAR(255) NOT NULL,
	data_criacao TIMESTAMP NOT NULL,
	data_modificacao TIMESTAMP NOT NULL,
	cor VARCHAR(7) NOT NULL,
	titulo VARCHAR(100) NOT NULL,
	CONSTRAINT categorias_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS backend.videos (
	id VARCHAR(255) NOT NULL,
	data_criacao TIMESTAMP NOT NULL,
	data_modificacao TIMESTAMP NOT NULL,
	titulo VARCHAR(50) NOT NULL,
	descricao VARCHAR(150) NOT NULL,
	url VARCHAR(100) NOT NULL,
	categoria_id VARCHAR(255) NOT NULL,
	CONSTRAINT videos_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS backend.videos
ADD CONSTRAINT categorias_fkey FOREIGN KEY (categoria_id) REFERENCES backend.categorias;
