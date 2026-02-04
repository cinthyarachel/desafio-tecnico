CREATE TABLE cartoes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero_cartao VARCHAR(50) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_cartoes_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
);