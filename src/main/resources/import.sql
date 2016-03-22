INSERT IGNORE INTO permission (permission_id, name) VALUES (1, 'CREATE NEW USER');
INSERT IGNORE INTO permission (permission_id, name) VALUES (2, 'DELETE USER');
INSERT IGNORE INTO permission (permission_id, name) VALUES (3, 'CREATE NEW ROLE');
INSERT IGNORE INTO permission (permission_id, name) VALUES (4, 'CREATE NEW TASK');
INSERT IGNORE INTO permission (permission_id, name) VALUES (5, 'CREATE NEW TASK REQUEST');
INSERT IGNORE INTO permission (permission_id, name) VALUES (6, 'CREATE NEW COMMENT');
INSERT IGNORE INTO role (role_id, name) VALUES (1, 'ADMIN');
INSERT IGNORE INTO role (role_id, name) VALUES (2, 'USER');
INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (1, 2);
INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (1, 3);
INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (1, 4);
INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (1, 5);
INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (1, 6);
INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (2, 4);
INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (2, 5);
INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (2, 6);
INSERT IGNORE INTO user (user_id, login, password, age, first_name, last_name, email, role_id)
VALUES (1, 'Alina', '1234', 21, 'Alina', 'Gorodetskaya', 'gorodecka_94@mail.ru', 1);
INSERT IGNORE INTO category (category_id, name) VALUES (1, 'Программирование');
INSERT IGNORE INTO category (category_id, name) VALUES (2, 'Переводы');
INSERT IGNORE INTO category (category_id, name) VALUES (3, 'Другое');
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (1, 'Java', 1);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (2, 'Scala', 1);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (3, 'C#', 1);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (4, 'C++', 1);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (5, 'Kotlin', 1);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (6, 'JavaScript', 1);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (7, 'PHP', 1);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (8, 'Python', 1);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (9, 'Английский', 2);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (10, 'Немецкий', 2);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (11, 'Французкий', 2);
INSERT IGNORE INTO subcategory (subcategory_id, name, category_id) VALUES (12, 'Латынь', 2);


