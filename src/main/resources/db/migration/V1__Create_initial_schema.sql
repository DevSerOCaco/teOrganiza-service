-- Create schemas
CREATE SCHEMA IF NOT EXISTS identity;
CREATE SCHEMA IF NOT EXISTS finance;

-- =====================================================================================
-- Schema: identity
-- =====================================================================================

-- Table: tb_roles
CREATE TABLE identity.tb_roles (
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

-- Table: tb_users
CREATE TABLE identity.tb_users (
    user_id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    fone VARCHAR(255),
    name VARCHAR(255),
    image_url VARCHAR(255),
    provider VARCHAR(255) NOT NULL
);

-- Join Table: tb_users_roles
CREATE TABLE identity.tb_users_roles (
    user_id UUID NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES identity.tb_users(user_id),
    FOREIGN KEY (role_id) REFERENCES identity.tb_roles(role_id)
);

-- =====================================================================================
-- Schema: finance
-- =====================================================================================

-- Table: accounts
CREATE TABLE finance.accounts (
    id UUID PRIMARY KEY,
    user_id UUID,
    name VARCHAR(255),
    balance DECIMAL(19, 2)
);
