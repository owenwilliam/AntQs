drop database if exists classdata;
create database classdata;
create table class_inf
(c_id integer auto_increment,
 c_name varchar(255),
 c_package varchar(255),
 primary key(c_id),
 unique(c_name)
);

insert into class_inf(c_name, c_package) values('ExternalConverter','com.owen.work.converter.impl.ExternalConverter');
insert into class_inf(c_name, c_package) values('InternalConverter','com.owen.work.converter.impl.InternalConverter');
commit;