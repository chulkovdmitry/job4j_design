create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('LG', '24000.95');
insert into devices(name, price) values('Samsung', '25000.05');
insert into devices(name, price) values('Sony', '30000.99');
insert into devices(name, price) values('Philips', '50000');

insert into people(name) values('Mark');
insert into people(name) values('Oleg');
insert into people(name) values('Peter');
insert into people(name) values('Ivan');
insert into people(name) values('Dennis');
insert into people(name) values('Theodor');

insert into devices_people(people_id, device_id) values(1, 1);
insert into devices_people(people_id, device_id) values(1, 2);
insert into devices_people(people_id, device_id) values(1, 3);

insert into devices_people(people_id, device_id) values(2, 3);
insert into devices_people(people_id, device_id) values(2, 3);
insert into devices_people(people_id, device_id) values(2, 1);

insert into devices_people(people_id, device_id) values(3, 1);
insert into devices_people(people_id, device_id) values(3, 4);

insert into devices_people(people_id, device_id) values(4, 2);

insert into devices_people(people_id, device_id) values(5, 2);
insert into devices_people(people_id, device_id) values(5, 4);

select avg(price) from devices;

select p.name, avg(d.price) from devices d
join devices_people dp on dp.device_id = d.id
join people p on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price) from devices d
join devices_people dp on dp.device_id = d.id
join people p on dp.people_id = p.id
group by p.name
having avg(d.price) > 26000;

having avg(d.price) > 5000;