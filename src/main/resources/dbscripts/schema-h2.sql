drop table if exists person;
create table person(
  id int auto_increment primary key,
  userName varchar(100),
  password varchar(100),
  nickName varchar(50),
  userType tinyint(3)
);

drop table if exists content;
create table content(
  id int auto_increment primary key,
  price double,
  title varchar(100),
  icon clob,
  abstract varchar(200),
  text clob
);

drop table if exists trx;
create table trx(
  id int auto_increment primary key,
  contentId int,
  personId int,
  price double,
  time timestamp
);
