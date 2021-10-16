CREATE TABLE IF NOT EXISTS cliente
(id_cliente INT AUTO_INCREMENT PRIMARY KEY,
nome_cliente VARCHAR(100),
data_nascimento DATE
);

INSERT IGNORE INTO cliente(nome_cliente, data_nascimento) VALUES ('Natalia', '1995-12-26');