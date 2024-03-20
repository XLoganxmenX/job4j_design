CREATE TABLE store (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    quantity INTEGER
);

INSERT INTO store (name, quantity) VALUES ('Молоко', 90);
INSERT INTO store (name, quantity) VALUES ('Сыр', 10);
INSERT INTO store (name, quantity) VALUES ('Колбаса', 13);

