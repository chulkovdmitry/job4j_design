create table albums(
    id serial primary key,
    name varchar(255),
    year int
);
create table producers(
    id serial primary key,
    name varchar(255)
);
create table bands(
    id serial primary key,
    name varchar(255),
    album_id int references albums(id),
    producer_id int references producers(id) unique
);
create table members(
	id serial primary key,
	name varchar(255),
	year_birth int,
	spec varchar(255)
);
create table bands_members(
    id serial primary key,
    year int,
    bands_id int references bands(id),
	member_id int references members(id)
);