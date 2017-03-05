CREATE TABLE `user` (
  `id` INT(8) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NULL DEFAULT NULL,
  `age` INT(11) NULL DEFAULT NULL,
  `isAdmin` BIT(1) NULL DEFAULT NULL,
  `createdDate` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  COLLATE='utf8_general_ci'
  ENGINE=InnoDB
  AUTO_INCREMENT=28
;

insert into user(name,age,isadmin,createddate) values('user 1',20,0,'2017-01-01');
insert into user(name,age,isadmin,createddate) values('user 2',23,0,'2017-02-01');
insert into user(name,age,isadmin,createddate) values('user 3',24,0,'2017-03-01');
insert into user(name,age,isadmin,createddate) values('user 4',25,0,'2017-02-01');
insert into user(name,age,isadmin,createddate) values('user 5',26,0,'2017-01-11');
insert into user(name,age,isadmin,createddate) values('user 6',27,0,'2017-01-31');
insert into user(name,age,isadmin,createddate) values('user 7',28,0,'2017-01-21');
insert into user(name,age,isadmin,createddate) values('user 8',29,0,'2017-01-10');
insert into user(name,age,isadmin,createddate) values('user 9',30,0,'2017-01-16');
insert into user(name,age,isadmin,createddate) values('user 10',40,0,'2017-01-18');
insert into user(name,age,isadmin,createddate) values('user 11',50,0,'2017-01-01');
