create table new_products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into new_products (name, producer, count, price)
        values ('first', 'test_product', 10, 549),
				('second', 'test_product', 18, 6469);
				
---------------------------------------------------------
-- 1 -- 

create or replace procedure del_data(p_name varchar(50))
language 'plpgsql' as
$$
    BEGIN
        delete from new_products
		WHERE name = p_name;
    END;
$$;

call del_data('first');

---------------------------------------------------------
-- 2 -- 

create or replace function del_data_by_price(p_low_price integer, p_high_price integer)
returns void
language 'plpgsql' as
$$
    BEGIN
        delete from new_products
		WHERE price between p_low_price and p_high_price;
    END;
$$;

SELECT del_data_by_price(5000, 6500);
