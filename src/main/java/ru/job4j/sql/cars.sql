create table body(
	id serial primary key,
	b_type varchar(255)
);
create table engine(
	id serial primary key,
	e_type varchar(255)
);
create table transmission(
	id serial primary key,
	t_type varchar(255)
);
create table vehicle(
	id serial primary key,
	brand varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(b_type) values ('minivan'), ('sedan'), ('hatchback'), ('jeep');
insert into engine(e_type) values ('1.6'), ('2.0'), ('2.4'), ('3.0');
insert into transmission(t_type) values ('automat'), ('mechanical'), ('robotic');
insert into vehicle(brand, body_id, engine_id, transmission_id) values('toyota', 1, 2, 2),
('audi', 2, 3, 1), ('chevrolet', 3, 1, 2), ('opel', 2, 1, 2);

select v.brand, c.b_type, e.e_type, t.t_type from vehicle v join body c on v.body_id = c.id
join engine e on v.engine_id = e.id join transmission t on v.transmission_id = t.id;

select * from body c left join vehicle v on c.id = v.body_id where v.body_id is null;
select * from engine e left join vehicle v on e.id = v.engine_id where v.engine_id is null; 
select * from transmission t left join vehicle v on t.id = v.transmission_id where v.transmission_id is null; 