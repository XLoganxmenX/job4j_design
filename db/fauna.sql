create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

INSERT INTO fauna (name, avg_age, discovery_date)
SELECT 
    'Species ' || id,
    CASE WHEN random() < 0.1 THEN null ELSE floor(random() * 100000) END as avg_age,
    CASE WHEN random() < 0.1 THEN null ELSE '1900-01-01'::date + floor(random() * (CURRENT_DATE - '1900-01-01'::date + 1)) * INTERVAL '1 day' END as discovery_date
FROM generate_series(501, 600) id;
INSERT INTO fauna (name, avg_age, discovery_date) values ('fish', 1000, '1902-02-02');

SELECT * FROM fauna
WHERE name = 'fish';

SELECT * FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

SELECT * FROM fauna
WHERE discovery_date IS null;

SELECT * FROM fauna
WHERE discovery_date < to_date('1950', 'yyyy');