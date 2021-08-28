create table type(
 	id serial primary key,
	name varchar(50)
);

create table product(
 	id serial primary key,
	name varchar(50),
	expired_date date,
	price float,
	type_id int references type(id)
);

insert into type(name) values('Сыр');
insert into type(name) values('Молоко');
insert into type(name) values('Мороженое');

insert into product(name, expired_date, price, type_id) values('Сыр Чеддер', '05.12.2021', '250.49', 1);
insert into product(name, expired_date, price, type_id) values('Сыр Эдам', '25.10.2021', '245.59', 1);
insert into product(name, expired_date, price, type_id) values('Сыр Российский', '15.11.2021', '270.78', 1);
insert into product(name, expired_date, price, type_id) values('Сыр Сливочный', '15.10.2021', '229.99', 1);
insert into product(name, expired_date, price, type_id) values('Сыр Голландский', '01.08.2021', '290.44', 1);
insert into product(name, expired_date, price, type_id) values('Сыр Голландский', '01.11.2021', '290.44', 1);

insert into product(name, expired_date, price, type_id) values('Молоко Славмо', '14.09.2021', '50.29', 2);
insert into product(name, expired_date, price, type_id) values('Молоко Домик в деревне', '23.07.2021', '60.39', 2);
insert into product(name, expired_date, price, type_id) values('Молоко Простоквашино', '09.10.2021', '70.34', 2);

insert into product(name, expired_date, price, type_id) values('Мороженое Крем-брюле', '21.08.2021', '50', 3);
insert into product(name, expired_date, price, type_id) values('Мороженое Пломбир брикет', '30.12.2021', '530', 3);
insert into product(name, expired_date, price, type_id) values('Мороженое Пломбир', '09.10.2021', '70', 3);

select * from product p join type t on t.id = p.type_id where t.name = 'Сыр';
select * from product where name like '%Мороженое%';
select * from product where expired_date < current_date;
select * from product where price = (select max(price) from product);

select t.name, count(t.id) from type t
join product p on t.id = p.type_id
group by t.name;

select * from product p
join type t on t.id = p.type_id
where t.name in ('Сыр', 'Молоко');

select t.name, count(t.id) from type t
join product p on t.id = p.type_id
group by t.name
having count(t.id) < 10;

select * from product p join type t on p.type_id = t.id