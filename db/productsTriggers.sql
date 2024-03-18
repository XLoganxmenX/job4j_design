create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

--------------------------------------
-------------------------------------- 1
create or replace function tax()
	returns trigger as
$$
	BEGIN
		update products
		set price = price * 1.1
		where id = (select id from inserted);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
	after insert 
	on products
	referencing new table as
                    inserted
	execute procedure tax();

insert into products (name, producer, count, price)
VALUES ('product_5', 'producer_5', 10, 100);

drop trigger tax_trigger on products;
------------------------------------ 2

create or replace function tax2()
	returns trigger as
$$
	BEGIN
		if NEW.price >= 200 THEN
			NEW.price = NEW.price * 1.5;
		end if;
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger2
	before insert 
	on products
	for each row
	execute procedure tax2();

insert into products (name, producer, count, price)
VALUES ('product_6', 'producer_6', 15, 200),
		('product_7', 'producer_7', 30, 400);

drop trigger tax_trigger2 on products;

------------------------------------ 3

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create or replace function add_price_hist()
returns trigger as
$$
	BEGIN
		insert into history_of_price(name, price, date) 
		values (new.name, new.price, CURRENT_TIMESTAMP);
		return null;
	END;
$$
LANGUAGE 'plpgsql';

create trigger price_hist_trigger
	after insert 
	on products
	for each row
	execute procedure add_price_hist();

insert into products (name, producer, count, price)
VALUES ('product_8', 'producer_8', 150, 1000);
insert into products (name, producer, count, price)
VALUES ('product_9', 'producer_9', 19, 8000);

SELECT * FROM history_of_price;

