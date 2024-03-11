CREATE TABLE agreement(
    isn serial primary key,
	id varchar(100),
    datebeg date,
	dateend date
);

INSERT INTO agreement(id, datebeg, dateend) VALUES ('101/4443512-1', '15.02.2024', '15.02.2025');

UPDATE agreement SET id = '103/4913654-2' WHERE isn = 1;
