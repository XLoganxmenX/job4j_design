CREATE TABLE car_bodies (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE car_engines (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE car_transmissions (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);


INSERT INTO car_bodies (name) VALUES
	('Седан'),
	('Хэтчбек'),
	('Универсал'),
	('Купе');

INSERT INTO car_engines (name) VALUES
	('Бензиновый'),
	('Дизельный'),
	('Электрический'),
	('Гибридный');

INSERT INTO car_transmissions (name) VALUES
	('Механическая'),
	('Автоматическая'),
	('Вариатор');

DO $$
DECLARE
    body INT;
    engine INT;
	transmission INT;
	randName varchar(255);
BEGIN
    FOR i IN 1..100 LOOP
		randName := 'Car ' || i;
        body := CASE WHEN random() < 0.1 THEN NULL ELSE (SELECT id FROM car_bodies ORDER BY RANDOM() LIMIT 1) END;
        engine := CASE WHEN random() < 0.1 THEN NULL ELSE (SELECT id FROM car_engines ORDER BY RANDOM() LIMIT 1) END;
        transmission := CASE WHEN random() < 0.1 THEN NULL ELSE (SELECT id FROM car_transmissions ORDER BY RANDOM() LIMIT 1) END;
        INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES (randName, body, engine, transmission);
    END LOOP;
END $$;

INSERT INTO car_bodies (name) VALUES
	('Лимузин');
INSERT INTO car_engines (name) VALUES
	('Газовый');
INSERT INTO car_transmissions (name) VALUES
	('Роботизированая');


SELECT * FROM CARS;

SELECT  c.id, 
		c.name AS car_name, 
		b.name AS body_name, 
		e.name AS engine_name, 
		t.name AS transmission_name 
FROM cars c
LEFT JOIN car_bodies b ON c.body_id = b.id
LEFT JOIN car_engines e ON c.engine_id = e.id
LEFT JOIN car_transmissions t ON c.transmission_id = t.id;

SELECT   
		b.name AS body_name 
FROM car_bodies b
WHERE NOT EXISTS (SELECT * FROM cars WHERE body_id = b.id);

SELECT   
		e.name AS engine_name 
FROM car_engines e
WHERE NOT EXISTS (SELECT * FROM cars WHERE engine_id = e.id);

SELECT   
		t.name AS transmission_name 
FROM car_transmissions t
WHERE NOT EXISTS (SELECT * FROM cars WHERE transmission_id = t.id);





