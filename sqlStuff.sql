use Camp_Manager;

## Holds session months
create table `session` (
	`session_month` varchar(10),
    `camper_fname` varchar(32),
    `camper_lname` varchar(32)
)engine=innodb;

## Hold bunk information
create table `bunkhouse` (
	`bunk_no` varchar(2)    
)engine=innodb;

## Holds camper information
#
CREATE TABLE `camper` (
                          `camper_id` INT(3) NOT NULL AUTO_INCREMENT,
                          `camper_fname` VARCHAR(32),
                          `camper_lname` VARCHAR(32),
                          `camper_age` INT(3),
                          `camper_dob` varchar(10),
                          `camper_docs` CHAR(2),
                          `session_month` varchar(10),
                          primary key (`camper_id`)
)  ENGINE=INNODB;



## Holds tribe information
create table `tribe` (
	`tribe_name` varchar(12)
) engine=innodb;
