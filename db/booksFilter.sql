create table students
(
    id   serial primary key,
    name varchar(50)
);

insert into students (name)
values ('Иван Иванов');
insert into students (name)
values ('Петр Петров');

create table authors
(
    id   serial primary key,
    name varchar(50)
);

insert into authors (name)
values ('Александр Пушкин');
insert into authors (name)
values ('Николай Гоголь');

create table books
(
    id serial primary key,
    name varchar(200),
    author_id integer references authors (id)
);

insert into books (name, author_id)
values ('Евгений Онегин', 1);
insert into books (name, author_id)
values ('Капитанская дочка', 1);
insert into books (name, author_id)
values ('Дубровский', 1);
insert into books (name, author_id)
values ('Мертвые души', 2);
insert into books (name, author_id)
values ('Вий', 2);

create table orders
(
    id serial primary key,
    active boolean default true,
    book_id integer references books (id),
    student_id integer references students (id)
);

insert into orders (book_id, student_id)
values (1, 1);
insert into orders (book_id, student_id)
values (3, 1);
insert into orders (book_id, student_id)
values (5, 2);
insert into orders (book_id, student_id)
values (4, 1);
insert into orders (book_id, student_id)
values (2, 2);


create view count_books_with_filter
as
	SELECT 
		a.name,
		count(s.name)
	from authors as a
			 join books b ON a.id = b.author_id
			 join orders o ON o.book_id = b.id
			 join students s ON s.id = o.student_id
	WHERE b.name NOT IN ('Мертвые души', 'Капитанская дочка')
	AND a.name <> 'Николай Гоголь'
	AND o.active = true
	AND LOWER(s.name) LIKE ('%иванов%') AND s.name <> 'петр петров'
	group by (a.name)
	having count(a.name) > 1;
	
SELECT name FROM count_books_with_filter
WHERE count >= 2;
         
         
