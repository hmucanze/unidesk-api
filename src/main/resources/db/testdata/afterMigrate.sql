

SET FOREIGN_KEY_CHECKS=0;

DELETE FROM categoria;
DELETE FROM utilizador;
DELETE FROM ocorrencia;

SET FOREIGN_KEY_CHECKS=1;

ALTER TABLE categoria AUTO_INCREMENT = 1;
ALTER TABLE utilizador AUTO_INCREMENT = 1;
ALTER TABLE ocorrencia AUTO_INCREMENT = 1;


INSERT INTO categoria(nome) VALUE("SIGAUZ"), ("Redes"), ("Equipamentos");

INSERT INTO utilizador(nome) VALUE("José Abudo");

INSERT INTO ocorrencia(codigo, descricao, prioridade, status, data_criacao, categoria_id, utilizador_id) 
VALUES("fa02222e-40ab-4751-8bf6-9d136107fa25", "Falha na renovação da senha", "ALTA", "PENDENTE", "2023-07-17 07:00:00", 1, 1);
INSERT INTO ocorrencia(codigo, descricao, prioridade, status, data_criacao, categoria_id, utilizador_id) 
VALUES("310f1cf2-4a05-49ee-b705-fc092a09f004", "Dificuldade de Conservação de nota", "MEDIA", "PENDENTE", "2023-07-17 10:34:00", 1, 1);