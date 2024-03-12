-- Добавление данных в таблицу users
INSERT INTO users (name) VALUES ('Alice');
INSERT INTO users (name) VALUES ('Bob');
INSERT INTO users (name) VALUES ('Charlie');

-- Добавление данных в таблицу roles
INSERT INTO roles (name, user_id) VALUES ('Admin', 1);
INSERT INTO roles (name, user_id) VALUES ('Editor', 2);
INSERT INTO roles (name, user_id) VALUES ('Viewer', 3);

-- Добавление данных в таблицу rules
INSERT INTO rules (name) VALUES ('Rule1');
INSERT INTO rules (name) VALUES ('Rule2');
INSERT INTO rules (name) VALUES ('Rule3');

-- Добавление данных в таблицу roles_rules
INSERT INTO roles_rules (role_id, rule_id) VALUES (1, 1);
INSERT INTO roles_rules (role_id, rule_id) VALUES (2, 2);
INSERT INTO roles_rules (role_id, rule_id) VALUES (3, 3);

-- Добавление данных в таблицу categories
INSERT INTO categories (name) VALUES ('Network');
INSERT INTO categories (name) VALUES ('DB');
INSERT INTO categories (name) VALUES ('System');

-- Добавление данных в таблицу states
INSERT INTO states (name) VALUES ('New');
INSERT INTO states (name) VALUES ('In work');
INSERT INTO states (name) VALUES ('Closed');

-- Добавление данных в таблицу items
INSERT INTO items (name, user_id, category_id, state_id) VALUES ('Cant open file', 1, 1, 1);
INSERT INTO items (name, user_id, category_id, state_id) VALUES ('Open VPN', 2, 2, 2);
INSERT INTO items (name, user_id, category_id, state_id) VALUES ('Dont install', 3, 3, 3);

-- Добавление данных в таблицу comments
INSERT INTO comments (name, item_id) VALUES ('Fast!', 1);
INSERT INTO comments (name, item_id) VALUES ('require approval', 2);
INSERT INTO comments (name, item_id) VALUES ('Interesting', 3);

-- Добавление данных в таблицу attachs
INSERT INTO attachs (link, item_id) VALUES ('http://example.com/image1.jpg', 1);
INSERT INTO attachs (link, item_id) VALUES ('http://example.com/image2.jpg', 2);
INSERT INTO attachs (link, item_id) VALUES ('http://example.com/book.pdf', 3);







