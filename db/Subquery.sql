CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country) 
VALUES 
('Alice', 'Smith', 25, 'USA'),
('Bob', 'Johnson', 30, 'Canada'),
('Charlie', 'Brown', 22, 'UK'),
('David', 'Lee', 22, 'Australia'),
('Emma', 'Garcia', 35, 'Spain'),
('Frank', 'Chen', 40, 'China'),
('Grace', 'Kim', 27, 'South Korea'),
('Henry', 'Martinez', 33, 'Mexico'),
('Ivy', 'Wong', 29, 'Singapore'),
('Jack', 'Nguyen', 31, 'Vietnam');

SELECT * FROM customers
WHERE age = (SELECT min(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id) 
VALUES 
(100, 12), 
(150, 13), 
(200, 12), 
(120, 15), 
(180, 14);

SELECT c.first_name, c.last_name
FROM customers c
WHERE id NOT IN(SELECT customer_id FROM orders);