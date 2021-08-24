insert into role(level) values('Junior');
insert into state(active) values('true');
insert into category(name) values('Test');
insert into users(name, age, role_id) values('Oleg', '33', 1);
insert into rules(admin) values('false');
insert into role_rules(role_id, rules_id) values(1, 1);
insert into item(
	number, edit_date, body, comments_id, user_id, category_id, state_id)
	values('1', '2021-08-24 10:23:54', 'Privet!', 1, 1, 1, 1);
insert into comments(edit_date, body, item_id) values('2021-08-24 19:23:54', 'Good evening!', 1);
insert into attachs(addition, item_id) values('Seek & Destroy', 1);