CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_cartoes_usuario_id ON cartoes(usuario_id);
CREATE INDEX idx_cartoes_status ON cartoes(status);
CREATE INDEX idx_cartoes_tipo ON cartoes(tipo);