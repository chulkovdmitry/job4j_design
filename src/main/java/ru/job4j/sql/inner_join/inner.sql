create table bands(
    id serial primary key,
    name varchar(255)
);

create table albums(
    id serial primary key,
    name varchar(255),
    year int,
    band_id int references bands(id)
);

insert into bands(name) values ('Deep Purple');
insert into bands(name) values ('Uriah Heep');
insert into bands(name) values ('Led Zeppelin');
insert into bands(name) values ('Black Sabbath');

insert into albums(name, year, band_id) values ('Machine Head', 1972, 1);
insert into albums(name, year, band_id) values ('Deep Purple in Rock', 1970, 1);
insert into albums(name, year, band_id) values ('Look at Yourself', 1971, 2);
insert into albums(name, year, band_id) values ('Demons and Wizards',1972, 2);
insert into albums(name, year, band_id) values ('The Magicians Birthday', 1972, 2);
insert into albums(name, year, band_id) values ('Led Zeppelin IV', 1971, 3);

select * from albums a join bands b on a.band_id = b.id;
select b.name as Название_группы, a.name as Название_альбома, a.year as Год
from albums as a join bands as b on a.band_id = b.id;
select b.name as Название_группы, a.name as Название_альбома, a.year as Год
from albums as a join bands as b on a.band_id = b.id where a.year = 1972;