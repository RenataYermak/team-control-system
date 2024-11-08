INSERT INTO position (id, name)
VALUES (1, 'Administrator'),
       (2, 'CEO'),
       (3, 'Security') ON CONFLICT (id) DO NOTHING;
SELECT SETVAL('position_id_seq', (SELECT MAX(id) FROM position));

INSERT INTO manager (id, firstname, lastname, birth_date, hire_date)
VALUES (1, 'Renata', 'Yermak', '1997-12-12', '2012-9-12'),
       (2, 'Kate', 'Zhuk', '1996-7-17', '2022-1-14'),
       (3, 'Alex', 'Budich', '1989-8-1', '2017-2-27'),
       (4, 'Maria', 'Shturo', '1993-10-2', '2023-4-2') ON CONFLICT (id) DO NOTHING;
SELECT SETVAL('manager_id_seq', (SELECT MAX(id) FROM manager));

INSERT INTO employee (id, firstname, lastname, birth_date, hire_date, manager_id)
VALUES (1, 'Lina', 'Yermak', '1997-12-12', '2012-9-12', 2),
       (2, 'Pavel', 'Zhuk', '1996-7-17', '2022-1-14', 2),
       (3, 'Kira', 'Budich', '1989-8-1', '2017-2-27', 1),
       (4, 'Tamara', 'Shturo', '1993-10-2', '2023-4-2', 3),
       (5, 'Olga', 'Egorov', '1991-11-30', '2007-11-23', 4),
       (6, 'Ruslan', 'Egorov', '1991-11-30', '2007-11-23', 1) ON CONFLICT (id) DO NOTHING;
SELECT SETVAL('employee_id_seq', (SELECT MAX(id) FROM employee));

INSERT INTO other_employee (id, firstname, lastname, birth_date, hire_date, position_id)
VALUES (1, 'Jimmy', 'Kimstach', '1997-12-12', '2012-9-12', 1),
       (2, 'Ivan', 'Nightly', '1996-7-17', '2022-1-14', 3),
       (3, 'Kris', 'Kooper', '1989-8-1', '2017-2-27', 2),
       (4, 'Mira', 'Smith', '1993-10-2', '2023-4-2', 3),
       (5, 'Nadin', 'Egorova', '1991-11-30', '2007-11-23', 2) ON CONFLICT (id) DO NOTHING;
SELECT SETVAL('other_employee_id_seq', (SELECT MAX(id) FROM other_employee));























