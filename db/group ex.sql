create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

INSERT INTO devices (name, price)
VALUES
    ('iPhone 13', 999.99),
    ('Samsung Galaxy', 899.99),
    ('MacBook Pro', 14999.99),
    ('Sony PlayStation', 4999.99),
    ('Bose Headphones', 3499.99);

INSERT INTO people (name)
VALUES
    ('John'),
    ('Emily'),
    ('Michael'),
    ('Sophia'),
    ('Daniel');

DO $$
DECLARE
    device_id INT;
    people_id INT;
BEGIN
    FOR i IN 1..100 LOOP
        SELECT id FROM devices ORDER BY RANDOM() LIMIT 1 INTO device_id;
        SELECT id FROM people ORDER BY RANDOM() LIMIT 1 INTO people_id;
        INSERT INTO devices_people (device_id, people_id) VALUES (device_id, people_id);
    END LOOP;
END $$;


SELECT AVG(price) FROM devices;

SELECT p.name, ROUND(AVG(d.price)::numeric, 3) AS av
FROM devices_people dp
JOIN people p ON p.id = dp.People_id
JOIN devices d ON d.id = dp.device_id
GROUP BY p.name;

SELECT p.name, ROUND(AVG(d.price)::numeric, 3) AS av
FROM devices_people dp
JOIN people p ON p.id = dp.People_id
JOIN devices d ON d.id = dp.device_id
GROUP BY p.name
HAVING ROUND(AVG(d.price)::numeric, 3) > 5000;


