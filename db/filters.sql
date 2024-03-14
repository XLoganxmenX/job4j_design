CREATE TABLE type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type_id INT REFERENCES type(id),
    expired_date DATE,
    price FLOAT
);

INSERT INTO type (name) VALUES ('СЫР'), ('МОЛОКО'), ('МОРОЖЕНОЕ'), ('КОНФЕТЫ'), ('МЯСО');

INSERT INTO product (name, type_id, expired_date, price) VALUES
    ('Сыр чеддер', 1, '2024-04-01', 5.99),
    ('Сыр гауда', 1, '2024-03-20', 4.49),
    ('Молоко ультрапастеризованное', 2, '2024-03-15', 2.99),
    ('Мороженое ванильное', 3, '2023-12-31', 3.49),
    ('Шоколад', 4, '2024-06-30', 1.99),
    ('Свинина', 5, '2024-04-10', 8.99),
    ('Молоко пастеризованное', 2, '2024-03-14', 2.49),
    ('Мороженое шоколадное', 3, '2024-02-28', 3.99),
    ('Сыр фета', 1, '2023-11-30', 6.99),
    ('Конфеты "Марс"', 4, '2024-05-31', 2.29),
	('Рулька', 5, '2024-04-11', 8.99);

-- 1 	
SELECT p.name
FROM product p
JOIN type t ON t.id = p.type_id
WHERE t.name = 'СЫР';

-- 2
SELECT name
FROM product
WHERE LOWER(name) LIKE ('%мороженое%');

-- 3
SELECT name, expired_date
FROM product
WHERE DATE(expired_date) < DATE(NOW())
ORDER BY expired_date;

-- 4
SELECT name, price
FROM product
WHERE price = (SELECT max(price) FROM product);

-- 5
SELECT t.name, COUNT(p.name)
FROM product p
JOIN type t ON t.id = p.type_id
GROUP BY p.type_id, t.name;

-- 6
SELECT p.name
FROM product p
JOIN type t ON t.id = p.type_id
WHERE t.name IN ('СЫР','МОЛОКО');

-- 7
SELECT t.name
FROM product p
JOIN type t ON t.id = p.type_id
GROUP BY p.type_id, t.name
HAVING COUNT(p.name) < 10;

-- 8
SELECT p.name, t.name
FROM product p
JOIN type t ON t.id = p.type_id;



