-- 1
CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id INT REFERENCES departments(id)
);

INSERT INTO departments (name) VALUES
    ('Отдел разработки'),
    ('Отдел маркетинга'),
    ('Отдел продаж');

INSERT INTO employees (name, department_id) VALUES
    ('Иван Иванов', 1),
    ('Петр Петров', 1),
    ('Анна Сидорова', 2),
    (null, 2),
    (null, 3),
    ('Михаил Новиков', null),
    ('Ольга Васильева', null);

-- 2
SELECT e.name, d.name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

SELECT e.name, d.name
FROM employees e
RIGHT JOIN departments d ON e.department_id = d.id;

SELECT e.name, d.name
FROM employees e
JOIN departments d ON e.department_id = d.id;

SELECT e.name, d.name
FROM employees e
CROSS JOIN departments d;

-- 3
SELECT COUNT(e.name), d.name
FROM departments d  
LEFT JOIN employees e ON e.department_id = d.id
GROUP BY d.name
HAVING COUNT(e.name) = 0;

-- 4
SELECT e.name, d.name
FROM employees e
RIGHT JOIN departments d ON e.department_id = d.id;

SELECT e.name, d.name
FROM departments d
LEFT JOIN employees e ON e.department_id = d.id;

-- 5
CREATE TABLE teens (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    gender VARCHAR(10)
);

INSERT INTO teens (name, gender) VALUES
    ('Вася', 'М'),
    ('Маша', 'Ж'),
    ('Петя', 'М'),
    ('Катя', 'Ж'),
    ('Саша', 'М'),
    ('Оля', 'Ж');
	
SELECT t1.name AS teen1, t1.gender AS gender1, t2.name AS teen2, t2.gender AS gender2
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender <> t2.gender
AND t1.id < t2.id;