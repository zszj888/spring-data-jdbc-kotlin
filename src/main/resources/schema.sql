create table  if NOT EXISTS  `contact` (
id int primary key auto_increment,
first_name varchar(50) not null,
last_name varchar(50) not null,
email varchar(50) not null);

create table  if NOT EXISTS  `company` (
id int primary key auto_increment,
`name` varchar(50) not null,
contact int(50) null);

create table  if NOT EXISTS  `status` (
id int primary key auto_increment,
`name` varchar(50) not null,
contact int(50) null);