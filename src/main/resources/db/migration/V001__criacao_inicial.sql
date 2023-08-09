
CREATE TABLE utilizador(
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE categoria(
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL,
	
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE ocorrencia(
	id BIGINT NOT NULL AUTO_INCREMENT,
	descricao TEXT,
	codigo VARCHAR(255) NOT NULL,
	prioridade VARCHAR(20) NOT NULL,
	status VARCHAR(20) NOT NULL,
	data_criacao DATETIME NOT NULL,
	data_resolucao DATETIME,
	data_cancelamento DATETIME,
	data_fechamento DATETIME,
	categoria_id BIGINT NOT NULL,
	utilizador_id BIGINT NOT NULL,
	
	PRIMARY KEY(id),
	
	CONSTRAINT fk_ocorrencia_categoria FOREIGN KEY(categoria_id) REFERENCES categoria(id),
	CONSTRAINT fk_ocorrencia_utilizador FOREIGN KEY(utilizador_id) REFERENCES utilizador(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
