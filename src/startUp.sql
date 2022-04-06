use Camp_Manager;

CREATE TABLE `camper` (
                          `camper_id` INT NOT NULL AUTO_INCREMENT,
                          `camper_fname` VARCHAR(32),
                          `camper_lname` VARCHAR(32),
                          `camper_age` INT(3),
                          `camper_house` CHAR(2),
                          `camper_docs` CHAR(2),
                          PRIMARY KEY (`camper_id`)
)  ENGINE=INNODB;

create table `male_bunk` (
                             `mbunk_id` int not null,
                             `camper_id` int not null,
                             `mbunk_house` char(2),
                             primary key (`mbunk_id`),
                             foreign key (`camper_id`) references camper(`camper_id`)
)engine=innodb;

create table `female_bunk` (
                               `fbunk_id` int not null,
                               `camper_id` int not null,
                               `fbunk_house` char(2),
                               primary key (`fbunk_id`),
                               foreign key (`camper_id`) references camper(`camper_id`)
)engine=innodb;