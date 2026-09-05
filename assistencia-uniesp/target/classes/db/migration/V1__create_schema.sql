-- ============================================================
-- V1: Esquema inicial do sistema de Assistencia Tecnica
-- ============================================================

CREATE TABLE cliente (
    id          BIGSERIAL PRIMARY KEY,
    nome        VARCHAR(150) NOT NULL,
    cpf         VARCHAR(14) NOT NULL UNIQUE,
    email       VARCHAR(150),
    ativo       BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE equipamento (
    id              BIGSERIAL PRIMARY KEY,
    tipo            VARCHAR(80) NOT NULL,
    marca           VARCHAR(80),
    modelo          VARCHAR(80),
    numero_serie    VARCHAR(100),
    cliente_id      BIGINT NOT NULL REFERENCES cliente(id)
);
CREATE INDEX idx_equipamento_cliente ON equipamento(cliente_id);

CREATE TABLE tecnico (
    id              BIGSERIAL PRIMARY KEY,
    nome            VARCHAR(150) NOT NULL,
    especialidade   VARCHAR(100),
    ativo           BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE ordem_servico (
    id                  BIGSERIAL PRIMARY KEY,
    status              VARCHAR(30) NOT NULL DEFAULT 'ABERTA',
    prioridade          VARCHAR(20) NOT NULL DEFAULT 'MEDIA',
    data_abertura       TIMESTAMP NOT NULL DEFAULT now(),
    data_conclusao      TIMESTAMP,
    descricao_defeito   TEXT,
    equipamento_id      BIGINT NOT NULL REFERENCES equipamento(id),
    tecnico_id          BIGINT REFERENCES tecnico(id)
);
CREATE INDEX idx_os_equipamento ON ordem_servico(equipamento_id);
CREATE INDEX idx_os_tecnico ON ordem_servico(tecnico_id);
CREATE INDEX idx_os_status ON ordem_servico(status);

CREATE TABLE historico_status (
    id                  BIGSERIAL PRIMARY KEY,
    status_anterior     VARCHAR(30),
    status_novo         VARCHAR(30) NOT NULL,
    data                TIMESTAMP NOT NULL DEFAULT now(),
    ordem_servico_id    BIGINT NOT NULL REFERENCES ordem_servico(id)
);
CREATE INDEX idx_historico_os ON historico_status(ordem_servico_id);

CREATE TABLE diagnostico (
    id                  BIGSERIAL PRIMARY KEY,
    descricao           TEXT NOT NULL,
    observacoes         TEXT,
    data                TIMESTAMP NOT NULL DEFAULT now(),
    ordem_servico_id    BIGINT NOT NULL UNIQUE REFERENCES ordem_servico(id)
);

CREATE TABLE orcamento (
    id                  BIGSERIAL PRIMARY KEY,
    valor_mao_obra      NUMERIC(10,2) NOT NULL DEFAULT 0,
    valor_pecas         NUMERIC(10,2) NOT NULL DEFAULT 0,
    status              VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    validade            DATE,
    ordem_servico_id    BIGINT NOT NULL UNIQUE REFERENCES ordem_servico(id)
);

CREATE TABLE peca (
    id          BIGSERIAL PRIMARY KEY,
    nome        VARCHAR(150) NOT NULL,
    codigo      VARCHAR(60) NOT NULL UNIQUE,
    preco       NUMERIC(10,2) NOT NULL,
    estoque     INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE item_ordem_servico (
    id                  BIGSERIAL PRIMARY KEY,
    ordem_servico_id    BIGINT NOT NULL REFERENCES ordem_servico(id),
    peca_id             BIGINT NOT NULL REFERENCES peca(id),
    quantidade          INTEGER NOT NULL DEFAULT 1,
    preco_unitario      NUMERIC(10,2) NOT NULL,
    UNIQUE (ordem_servico_id, peca_id)
);
CREATE INDEX idx_item_os ON item_ordem_servico(ordem_servico_id);
CREATE INDEX idx_item_peca ON item_ordem_servico(peca_id);

CREATE TABLE anexo (
    id                  BIGSERIAL PRIMARY KEY,
    nome                VARCHAR(200) NOT NULL,
    tipo                VARCHAR(100),
    tamanho             BIGINT,
    storage_key         VARCHAR(300) NOT NULL,
    data_upload         TIMESTAMP NOT NULL DEFAULT now(),
    ordem_servico_id    BIGINT NOT NULL REFERENCES ordem_servico(id)
);
CREATE INDEX idx_anexo_os ON anexo(ordem_servico_id);
