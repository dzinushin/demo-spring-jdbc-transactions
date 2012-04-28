
create table customer
(
	id int not null,
	name varchar(120) not null
) engine innodb;


create table account
(
	id int not null,
	sum decimal(10,2) not null,
	customer_id int not null,
	created datetime not null,
	modified datetime not null default now()
) engine innodb; 

insert into customer() values(1,'customer1');
insert into customer() values(2,'customer2');

insert into account(id,sum,customer_id,created,modified) values(1,100,1,now(), now());
insert into account(id,sum,customer_id,created,modified) values(2,0,1,now(), now());