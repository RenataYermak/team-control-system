--liquibase formatted sql

--changeset r.yermak:5
-- Insert initial data into 'position' table
INSERT INTO position (id, name)
VALUES (1, 'Administrator'),
       (2, 'CEO'),
       (3, 'Security');
SELECT SETVAL('position_id_seq', (SELECT MAX(id) FROM position));
--rollback DELETE FROM position;

--changeset r.yermak:6
-- Insert initial data into 'manager' table
INSERT INTO manager (id, firstname, lastname, birth_date, hire_date)
VALUES (1, 'Renata', 'Yermak', '1997-12-12', '2012-09-12'),
       (2, 'Kate', 'Zhuk', '1996-07-17', '2022-01-14'),
       (3, 'Alex', 'Budich', '1989-08-01', '2017-02-27'),
       (4, 'Maria', 'Shturo', '1993-10-02', '2023-04-02');
SELECT SETVAL('manager_id_seq', (SELECT MAX(id) FROM manager));
--rollback DELETE FROM manager;

--changeset r.yermak:7
-- Insert initial data into 'employee' table
INSERT INTO employee (id, firstname, lastname, birth_date, hire_date, manager_id)
VALUES (1, 'Lina', 'Yermak', '1997-12-12', '2012-09-12', 2),
       (2, 'Pavel', 'Zhuk', '1996-07-17', '2022-01-14', 2),
       (3, 'Kira', 'Budich', '1989-08-01', '2017-02-27', 1),
       (4, 'Tamara', 'Shturo', '1993-10-02', '2023-04-02', 3),
       (5, 'Olga', 'Egorov', '1991-11-30', '2007-11-23', 4),
       (6, 'Ruslan', 'Egorov', '1991-11-30', '2007-11-23', 1);
SELECT SETVAL('employee_id_seq', (SELECT MAX(id) FROM employee));
--rollback DELETE FROM employee;

--changeset r.yermak:8
-- Insert initial data into 'other_employee' table
INSERT INTO other_employee (id, firstname, lastname, birth_date, hire_date, position_id)
VALUES (1, 'Jimmy', 'Kimstach', '1997-12-12', '2012-09-12', 1),
       (2, 'Ivan', 'Nightly', '1996-07-17', '2022-01-14', 3),
       (3, 'Kris', 'Kooper', '1989-08-01', '2017-02-27', 2),
       (4, 'Mira', 'Smith', '1993-10-02', '2023-04-02', 3),
       (5, 'Nadin', 'Egorova', '1991-11-30', '2007-11-23', 2);
SELECT SETVAL('other_employee_id_seq', (SELECT MAX(id) FROM other_employee));
--rollback DELETE FROM other_employee;
