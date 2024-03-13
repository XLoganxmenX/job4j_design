create table board_games
(
    id serial primary key,
    game varchar(255),
	amount int
);

create table sale
(
    id serial primary key,
    board_game int references board_games(id),
	datebeg date,
	dateend date
);

insert into board_games(game, amount)
VALUES ('Tavern Red Dragon', 5), 
		('Munchkin', 20),
		('Pathfinder', 3),
		('Dixit', 19);
		
insert into sale(board_game, datebeg, dateend)
VALUES (1, '2024-02-03', '2024-03-03'), 
		(3, '2024-01-01', '2024-01-15');


SELECT bg.game AS "board game",
	   s.datebeg AS "sale start",
	   s.dateend AS "sale end"
FROM sale s
JOIN board_games bg ON s.board_game = bg.id;

SELECT bg.game AS "board game",
	   bg.amount AS "remains",
	   s.dateend AS "deadline"
FROM sale s
JOIN board_games bg ON s.board_game = bg.id;

SELECT bg.game AS "board game",
	   bg.amount AS "amount",
	   s.datebeg AS "start"
FROM sale s
JOIN board_games bg ON s.board_game = bg.id
WHERE amount > 3;