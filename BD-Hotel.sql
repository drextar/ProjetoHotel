CREATE DATABASE HOTEL;
USE HOTEL;

CREATE TABLE HOSPEDE(
    CPF CHAR(14) PRIMARY KEY,
    RG CHAR(12),
    NOME VARCHAR(30),
    EMAIL VARCHAR(60),
    SEXO CHAR(1) DEFAULT 'M',
    DATANASC CHAR(10),
    TELEFONE CHAR(13)
);

CREATE TABLE QUARTO(
    NUMQUARTO CHAR(3) PRIMARY KEY,
    TIPOQUARTO VARCHAR(30),
    DESCRICAO VARCHAR(120),
    VALORDIARIA DECIMAL(5,2),
    STATUSQUARTO CHAR(15)
);

CREATE TABLE DIARIAS(
    IDDIARIA CHAR(3) PRIMARY KEY,
    VALORSERVICO DECIMAL(5,2),
    VALORQUARTO DECIMAL(5,2),
    VALORTOTAL DECIMAL(6,2)
);

CREATE TABLE HOTEL(
    CPNJ CHAR(11) PRIMARY KEY,
    NOMEHOTEL VARCHAR(30),
    ENDERECO VARCHAR(100)
);

CREATE TABLE SERVICOS(
    IDSERVICO  INT PRIMARY KEY AUTO_INCREMENT,
    NOMESERVICO VARCHAR(30),
    TIPOSERVICO VARCHAR(30),
    VALORSERVICO DECIMAL(5,2)
);

CREATE TABLE CADASTRO(
    IDUSUARIO CHAR(3) PRIMARY KEY,
    SENHA VARCHAR(30)
);

CREATE TABLE HOSPEDESERVICO(
    CPF CHAR(14),
    IDSERVICO INT,
    VALORSERVICO DECIMAL(5,2),
    PRIMARY KEY(CPF,IDSERVICO),
    FOREIGN KEY (CPF) REFERENCES HOSPEDE(CPF),
    FOREIGN KEY (IDSERVICO) REFERENCES SERVICOS(IDSERVICO)
);

CREATE TABLE HOSPEDERESERVA(
    CPF CHAR(14),
    NUMQUARTO CHAR(3),
    VALORTOTAL DECIMAL(6,2),
    DATAENTRADA CHAR(8),
    DATASAIDA CHAR(8),
    PRIMARY KEY(CPF,NUMQUARTO),
    FOREIGN KEY (CPF) REFERENCES HOSPEDE(CPF),
    FOREIGN KEY (NUMQUARTO) REFERENCES QUARTO(NUMQUARTO)
);

CREATE TABLE QUITADIARIAS(
    CPF CHAR(11),
    IDDIARIA CHAR(3),
    TOTALPAGAR DECIMAL(6,2),
    PRIMARY KEY(CPF,IDDIARIA),
    FOREIGN KEY (CPF) REFERENCES HOSPEDE(CPF),
    FOREIGN KEY (IDDIARIA) REFERENCES DIARIAS(IDDIARIA)
);

CREATE TABLE HOSPEDECADASTRO(
    CPF CHAR(11),
    IDUSUARIO CHAR(3),
    PRIMARY KEY(CPF,IDUSUARIO),
    FOREIGN KEY (CPF) REFERENCES HOSPEDE(CPF),
    FOREIGN KEY (IDUSUARIO) REFERENCES CADASTRO(IDUSUARIO)
);

INSERT INTO HOSPEDE VALUES 
('474.324.234-07', '52.756.145-9', 'Vinicius Chiarotti', 'vinicius@outlook.com', 'M', '1998-10-21', '119999-9999'),
('379.567.444-09', '53.890.164-8', 'Larissa Nunes', 'larissa@outlook.com', 'F', '1994-06-15', '118888-8888'),
('123.776.742-03', '54.516.361-7', 'Matheus Encinas', 'matheus@outlook.com', 'M', '1999-04-18', '117777-7777'),
('524.984.524-09', '55.624.893-6', 'Simone Velosa', 'simone@outlook.com', 'F', '1990-12-21', '116666-6666'),
('231.451.625-05', '56.908.124-5', 'Henrique Guirelli', 'henrique@outlook.com', 'M', '1996-08-10', '115555-5555');

INSERT INTO QUARTO VALUES
('01','LUXO', 'QUARTO DE LUXO', 200.90, 'Disponivel'),
('02','LUXO', 'QUARTO DE LUXO', 210.60, 'Disponivel'),
('03','LUXO', 'QUARTO DE LUXO', 220.00, 'Disponivel'),
('04','SIMPLES', 'QUARTO SIMPLES', 140, 'Disponivel'),
('05','SIMPLES', 'QUARTO SIMPLES', 150.00, 'Alugado'),
('06','SIMPLES', 'QUARTO SIMPLES', 160.00, 'Disponivel'),
('07','SIMPLES', 'QUARTO SIMPLES', 170.00, 'Disponivel'),
('08','SIMPLES', 'QUARTO SIMPLES', 180.00, 'Disponivel');

INSERT INTO SERVICOS VALUES
(NULL, 'SERVICO DE QUARTO', 'HOTEL', 50.00),
(NULL, 'CAFE DA MANHA', 'HOTEL', 50.00),
(NULL, 'ALMOCO', 'HOTEL', 50.00),
(NULL, 'JANTA', 'HOTEL', 50.00),
(NULL, 'SUCO', 'FRIGOBAR', 10.00),
(NULL, 'REFRIGERANTE', 'FRIGOBAR', 10.00),
(NULL, 'CHOCOLATE', 'FRIGOBAR', 8.00),
(NULL, 'BISCOITO', 'FRIGOBAR', 6.00);

INSERT INTO HOSPEDERESERVA VALUES
('474.324.234-07','01', '1250.00', '20200101', '20200105'),
('379.567.444-09','02', '2330.00', '20200101', '20200108'),
('123.776.742-03','03', '970.00', '20200101', '20200109'),
('524.984.524-09','04', '3320.00', '20200101', '20200110'),
('231.451.625-05','05', '7890.00', '20290101', '20200111');

SELECT * FROM HOSPEDE;
SELECT * FROM QUARTO;
SELECT * FROM SERVICOS;
SELECT * FROM HOSPEDERESERVA;