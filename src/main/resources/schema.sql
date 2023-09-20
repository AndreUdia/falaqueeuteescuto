CREATE TABLE cliente
(
    id              BIGINT NOT NULL,
    uuid            UUID,
    nome            VARCHAR(255),
    email           VARCHAR(255),
    data_nascimento date,
    CONSTRAINT pk_cliente PRIMARY KEY (id)
);