-- V1: Create initial schema for identity and finance modules

-- Create schemas
CREATE SCHEMA IF NOT EXISTS identity;
CREATE SCHEMA IF NOT EXISTS finance;

-- =====================================================================================
-- Schema: identity
-- =====================================================================================

-- Table: roles
CREATE TABLE identity.roles (
    id UUID PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

-- Table: users
CREATE TABLE identity.users (
    user_id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    fone VARCHAR(255),
    name VARCHAR(255),
    image_url VARCHAR(255),
    provider VARCHAR(255) NOT NULL
);

-- Join Table: users_roles
CREATE TABLE identity.users_roles (
    user_id UUID NOT NULL REFERENCES identity.users(user_id),
    role_id UUID NOT NULL REFERENCES identity.roles(id),
    PRIMARY KEY (user_id, role_id)
);

-- =====================================================================================
-- Schema: finance
-- =====================================================================================

-- Table: account
CREATE TABLE finance.account (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(255),
    balance DECIMAL(19, 2)
);

-- Table: category
CREATE TABLE finance.category (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(255),
    father_id UUID REFERENCES finance.category(id)
);

-- Table: transaction_groups
CREATE TABLE finance.transaction_groups (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    description VARCHAR(100) NOT NULL
);

-- Table: recurrence_rules
CREATE TABLE finance.recurrence_rules (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    description VARCHAR(100) NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    type VARCHAR(10) NOT NULL,
    frequency VARCHAR(10) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    day_of_month INT,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    next_generation_date DATE NOT NULL,
    account_id UUID NOT NULL REFERENCES finance.account(id),
    category_id UUID NOT NULL REFERENCES finance.category(id),
    transaction_group_id UUID NOT NULL REFERENCES finance.transaction_groups(id)
);

-- Table: transactions
CREATE TABLE finance.transactions (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    description VARCHAR(100) NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    date DATE NOT NULL,
    type VARCHAR(10) NOT NULL,
    status VARCHAR(10) NOT NULL,
    account_id UUID NOT NULL REFERENCES finance.account(id),
    category_id UUID NOT NULL REFERENCES finance.category(id),
    transaction_group_id UUID REFERENCES finance.transaction_groups(id),
    recurrence_rule_id UUID REFERENCES finance.recurrence_rules(id)
);

-- Table: payments
CREATE TABLE finance.payments (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    transaction_id UUID NOT NULL REFERENCES finance.transactions(id),
    amount DECIMAL(19, 2) NOT NULL,
    payment_date DATE NOT NULL,
    description VARCHAR(150),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
