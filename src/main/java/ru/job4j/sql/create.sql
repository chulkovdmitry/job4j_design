create table grup(
	id serial primary key,
	name varchar(255),
	track float8,
	year int,
	style text,
	active boolean
);