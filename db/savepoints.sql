begin transaction;

create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

select * from products;

savepoint before_insert;
-----------------------------------------------------------

insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 20, 19);
insert into products (name, producer, count, price) VALUES ('product_7', 'producer_7', 450, 18);
insert into products (name, producer, count, price) VALUES ('product_8', 'producer_8', 17, 100);

select * from products;

savepoint before_update;
----------------------------------------------------------

update products SET price = price * 1.2
WHERE price >= 19;
select * from products;

rollback to before_update;
select * from products;

savepoint before_delete;
----------------------------------------------------------

delete from products;
select * from products;

rollback to before_update;
select * from products;

rollback to before_insert;
select * from products;

commit;
