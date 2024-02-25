
CREATE TABLE if NOT exists `company` (
	`company_id` INT(10) primary key NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci'
);

create table if NOT exists `status`(
status_id int primary key auto_increment,
name varchar(50) not null);

create table  if NOT EXISTS  `contact` (
id int primary key auto_increment,
first_name varchar(50) not null,
last_name varchar(50) not null,
email varchar(50) not null);