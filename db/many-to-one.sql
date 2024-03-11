CREATE TABLE car (
    id serial primary key,
	model varchar(255),
	year date
);

CREATE TABLE client (
    id serial primary key,
	name varchar(255),
	car int references car(id)
);

INSERT INTO car(model, year) VALUES ('BMV', to_date('2008', 'yyyy'));
INSERT INTO client(name, car) VALUES ('Amelia', 1);

SELECT * FROM client
JOIN client ;


