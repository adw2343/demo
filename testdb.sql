CREATE DATABASE IF NOT EXISTS testdb DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
use testdb;
drop table if exists author_to_book;
drop table if exists Book;
drop table if exists Person;

create table  if not exists Person(
	id bigint(20) not null primary key auto_increment,
	name varchar(80) default null,
	create_time datetime not null,
	update_on datetime not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

create table  if not exists Book(
	id bigint(20) not null primary key auto_increment,
	name varchar(80) default null,
	create_time datetime not null,
	update_on datetime not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists author_to_book(
	id bigint(20) not null primary key auto_increment,
	book bigint(20) not null,
	author bigint(20) not null,
	create_time datetime not null,
	update_on datetime not null,
	foreign key `fk_book_author`(`author`) references `Person`(`id`) on delete cascade,
	foreign key `fk_author_book`(`book`) references `Book`(`id`) on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE DATABASE IF NOT EXISTS testdb1 DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
use testdb1;
drop table if exists student_to_teacher;
drop table if exists Teacher;
drop table if exists Student;
create table  if not exists Teacher(
	id bigint(20) not null primary key auto_increment,
	name varchar(80) default null,
	create_time datetime not null,
	update_on datetime not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

create table  if not exists Student(
	id bigint(20) not null primary key auto_increment,
	name varchar(80) default null,
	create_time datetime not null,
	update_on datetime not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;


create table  if not exists student_to_teacher(
	id bigint(20) not null primary key auto_increment,
	student bigint(20) not null,
	teacher bigint(20) not null,
	foreign key `fk_student_teacher`(`teacher`) references `Teacher`(`id`) on delete cascade,
	foreign key `fk_teacher_student`(`student`) references `Student`(`id`) on delete cascade
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;





