-- V2: Adiciona as entidades TransactionGroup, RecurrenceRule e Payment

-- Tabela para agrupar transações (ex: parcelamentos, assinaturas)
CREATE TABLE transaction_groups (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    CONSTRAINT fk_transaction_groups_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Tabela para regras de recorrência (transações automáticas)
CREATE TABLE recurrence_rules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    type VARCHAR(10) NOT NULL,
    frequency VARCHAR(10) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    day_of_month INT,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    next_generation_date DATE NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    account_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    transaction_group_id BIGINT,
    CONSTRAINT fk_recurrence_rules_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_recurrence_rules_account FOREIGN KEY (account_id) REFERENCES accounts(id),
    CONSTRAINT fk_recurrence_rules_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_recurrence_rules_transaction_group FOREIGN KEY (transaction_group_id) REFERENCES transaction_groups(id)
);

-- Tabela para registrar pagamentos parciais de uma transação
CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_id BIGINT NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    payment_date DATE NOT NULL,
    description VARCHAR(150),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_payments_transaction FOREIGN KEY (transaction_id) REFERENCES transactions(id)
);

-- Adiciona as colunas de relacionamento na tabela de transações
-- Garante que a coluna transaction_group_id exista
ALTER TABLE transactions ADD COLUMN IF NOT EXISTS transaction_group_id BIGINT;

-- Garante que a coluna recurrence_rule_id exista
ALTER TABLE transactions ADD COLUMN IF NOT EXISTS recurrence_rule_id BIGINT;

-- Adiciona as chaves estrangeiras se elas não existirem
-- Uma constraint com nome duplicado pode causar erro, então checamos antes ou usamos nomes únicos.
-- A forma de checar pode variar com o DB, então uma abordagem segura é simplesmente garantir que as colunas existem
-- e assumir que a aplicação gerenciará as relações. Para Flyway, o ideal é ser explícito.

-- Como V1 não define essas FKs, podemos adicioná-las diretamente.
ALTER TABLE transactions ADD CONSTRAINT fk_transactions_transaction_group FOREIGN KEY (transaction_group_id) REFERENCES transaction_groups(id);
ALTER TABLE transactions ADD CONSTRAINT fk_transactions_recurrence_rule FOREIGN KEY (recurrence_rule_id) REFERENCES recurrence_rules(id);

