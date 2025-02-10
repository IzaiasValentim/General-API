INSERT INTO USERS (username, CPF, email, password)
VALUES ('izaiasADM',
        '1231231',
        'izaias@gmail.com',
        '$2a$10$GiseHkdvwOFr7A9KRWbeiOmg/PYPhWVjdm42puLfOzR/gIAQrsAGy'),
       ('izaiasMAN',
        '1231232',
        'izaiasMAN@gmail.com',
        '$2a$10$GiseHkdvwOFr7A9KRWbeiOmg/PYPhWVjdm42puLfOzR/gIAQrsAGy'),
       ('izaiasSEL',
        '1231233',
        'izaiasSEL@gmail.com',
        '$2a$10$GiseHkdvwOFr7A9KRWbeiOmg/PYPhWVjdm42puLfOzR/gIAQrsAGy'),
       ('izaiasINT',
        '1231234',
        'izaiasINT@gmail.com',
        '$2a$10$GiseHkdvwOFr7A9KRWbeiOmg/PYPhWVjdm42puLfOzR/gIAQrsAGy');

INSERT INTO API_USER(user_id, phone, address, admission_date, is_admin, is_active, is_deleted)
VALUES (1, '8888888', 'casa', '2025-02-03', TRUE, TRUE, FALSE),
       (2, '88888889', 'casa', '2025-02-03', FALSE, TRUE, FALSE),
       (3, '88888889', 'casa', '2025-02-03', FALSE, TRUE, FALSE),
       (4, '88888889', 'casa', '2025-02-03', FALSE, TRUE, FALSE);

INSERT INTO ROLES(name) VALUES ('ADMINISTRATOR');
INSERT INTO ROLES(name) VALUES ('MANAGER');
INSERT INTO ROLES(name) VALUES ('SELLER');
INSERT INTO ROLES(name) VALUES ('INTERN');

INSERT INTO tb_users_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);

