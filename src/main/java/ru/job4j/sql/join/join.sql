create table departments(
	id serial primary key,
	name varchar(50)
);

create table employees(
	id serial primary key,
	name varchar(50),
	department_id int references departments(id)
);

insert into departments(name) values('IT');
insert into departments(name) values('HR');
insert into departments(name) values('Managers');
insert into departments(name) values('Sales');
insert into departments(name) values('Shipping');

insert into employees(name, department_id) values ('Oleg', 1), ('Victor', 1);
insert into employees(name, department_id) values ('Vlad', 2), ('Alex', 2);
insert into employees(name, department_id) values ('Keks', null), ('Rex', null);
insert into employees(name, department_id) values ('Petr', 3), ('Pavel', 3);
insert into employees(name, department_id) values ('Sasha', 4), ('Shurik', 4);

select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;
select * from employees e full join departments d on e.department_id = d.id;
select * from employees cross join departments;

select * from departments d left join employees e on e.department_id = d.id where e.id is null;

select * from employees e left join departments d  on  e.department_id = d.id;
select * from departments d right join employees e on d.id = e.department_id;

create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('Pavel', 'm'), ('Petr', 'm');
insert into teens(name, gender) values ('Oksana', 'f'), ('Nadya', 'f');

select t1.name, t1.gender, t2.name, t2.gender from teens t1
cross join teens t2 where not t1.gender = t2.gender;