create database JDBC;

use jdbc;
create table Book (
id int primary key auto_increment,
title nvarchar(50), 
yearOfPublish date,
publisher nvarchar(50),
author_id int,
foreign key (author_id) references author(id)
);

create table Author (
id int primary key auto_increment,
authorName nvarchar(50), 
country nvarchar(50)
);