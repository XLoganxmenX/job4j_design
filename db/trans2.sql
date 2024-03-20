BEGIN transaction isolation level serializable;
SELECT * FROM store;

update store set quantity = quantity - 50
WHERE name = 'Молоко';

commit;