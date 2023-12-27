CREATE DATABASE md01_demo_orm;
use md01_demo_orm;

CREATE TABLE category(
    id int primary key  auto_increment,
    name varchar(100) not null,
    status bit(1) default 1
);

 CREATE TABLE product(
     id int primary key  auto_increment,
     name varchar(255) NOT NULL ,
     price float not null ,
     category_id int not null ,
     foreign key (category_id)references category(id)
 );