--liquibase formatted sql

--changeset r.yermak:1
CREATE TABLE manager
(
    id         BIGSERIAL PRIMARY KEY,
    lastname   VARCHAR(25) NOT NULL,
    firstname  VARCHAR(25) NOT NULL,
    birth_date DATE        NOT NULL,
    hire_date  DATE        NOT NULL
);
--rollback DROP TABLE IF EXISTS manager;

--changeset r.yermak:2
CREATE TABLE employee
(
    id         BIGSERIAL PRIMARY KEY,
    lastname   VARCHAR(25) NOT NULL,
    firstname  VARCHAR(25) NOT NULL,
    birth_date DATE        NOT NULL,
    hire_date  DATE        NOT NULL,
    manager_id BIGINT,
    FOREIGN KEY (manager_id) REFERENCES manager (id)
);
--rollback DROP TABLE IF EXISTS employee;

--changeset r.yermak:3
CREATE TABLE position
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);
--rollback DROP TABLE IF EXISTS position;

--changeset r.yermak:4
CREATE TABLE other_employee
(
    id          BIGSERIAL PRIMARY KEY,
    lastname    VARCHAR(25) NOT NULL,
    firstname   VARCHAR(25) NOT NULL,
    birth_date  DATE        NOT NULL,
    hire_date   DATE        NOT NULL,
    position_id INTEGER     NOT NULL REFERENCES position (id)
);
--rollback DROP TABLE IF EXISTS other_employee;

