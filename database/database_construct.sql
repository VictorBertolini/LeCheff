CREATE DATABASE IF NOT EXISTS le_cheff;
USE le_cheff;

CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE Endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    rua VARCHAR(150) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    complemento VARCHAR(100),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE
);

CREATE TABLE Prato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

CREATE TABLE Cheff (
    email VARCHAR(100) PRIMARY KEY,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE Endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cidade VARCHAR(100) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    rua VARCHAR(150) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    complemento VARCHAR(100)
);

CREATE TABLE Pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    endereco_id INT NOT NULL,
    qnt_pessoas INT NOT NULL,
    observacoes TEXT,
    data_hora DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Pendente',
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE,
    FOREIGN KEY (endereco_id) REFERENCES Endereco(id)
);

CREATE TABLE PedidoPrato (
    pedido_id INT NOT NULL,
    prato_id INT NOT NULL,
    PRIMARY KEY (pedido_id, prato_id),
    FOREIGN KEY (pedido_id) REFERENCES Pedido(id) ON DELETE CASCADE,
    FOREIGN KEY (prato_id) REFERENCES Prato(id)
);