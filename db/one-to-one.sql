create table ticket(
    id serial primary key,
    position int
);

create table passangers(
    id serial primary key,
    name varchar(255),
    ticket_id int references ticket(id) unique
);

INSERT INTO ticket(position) VALUES (10);
INSERT INTO passangers(name, ticket_id) VALUES ('F.S.D.', 1);