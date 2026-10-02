-- Script de criação do banco de dados do sistema de Tarefas
-- SGBD: PostgreSQL 16
-- Execute como superusuário antes de subir a aplicação:
--   psql -U postgres -f schema.sql

CREATE DATABASE atividade_tarefas;

\c atividade_tarefas;

CREATE SCHEMA IF NOT EXISTS tarefas;

CREATE TABLE IF NOT EXISTS tarefas.tarefa (
    id                BIGSERIAL PRIMARY KEY,
    nome              VARCHAR(255) NOT NULL,
    descricao         VARCHAR(255),
    observacao        VARCHAR(255),
    data_criacao      TIMESTAMP,
    data_atualizacao  TIMESTAMP,
    status            VARCHAR(50)
);

-- Dados de exemplo (opcional)
INSERT INTO tarefas.tarefa (nome, descricao, observacao, data_criacao, data_atualizacao, status)
VALUES
    ('Estudar Spring Boot', 'Revisar controllers e services', 'Prioridade alta', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PENDENTE'),
    ('Configurar PostgreSQL', 'Criar banco atividade_tarefas', 'Usar script schema.sql', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'CONCLUIDO');
