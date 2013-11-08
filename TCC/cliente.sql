CREATE DATABASE tcc;
USE tcc;

CREATE TABLE cliente(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50),
	email VARCHAR(50)
);

INSERT INTO cliente (nome, email) VALUES ('daniel', 'e@mail');
INSERT INTO cliente (nome, email) VALUES ('siqueira', 'e@mail');

SELECT * FROM cliente;