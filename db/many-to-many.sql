CREATE TABLE payment (
    id serial primary key,
	client varchar(255),
	amount int
);

CREATE TABLE invoice_status (
    id serial primary key,
	status_name varchar(255)
);

CREATE TABLE invoice (
    id serial primary key,
	payment_id int references payment(id),
	status int references invoice_status(id)
);

INSERT INTO payment(id, client, amount) VALUES (345, 'Popov Arsen Ivanovich', 1000);
INSERT INTO invoice_status(status_name) VALUES ('complete');
INSERT INTO invoice(payment_id, status) VALUES (345, 1);

