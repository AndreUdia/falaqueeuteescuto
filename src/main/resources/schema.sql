CREATE SEQUENCE CLIENTE_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE cliente
(
    id              BIGINT NOT NULL,
    uuid            UUID,
    nome            VARCHAR(255),
    email           VARCHAR(255),
    data_nascimento date,
    CONSTRAINT pk_cliente PRIMARY KEY (id)
);


CREATE SEQUENCE empresa_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE empresa
(
    id    BIGINT NOT NULL,
    uuid  UUID,
    nome  VARCHAR(255),
    setor VARCHAR(255),
    CONSTRAINT pk_empresa PRIMARY KEY (id)
);
