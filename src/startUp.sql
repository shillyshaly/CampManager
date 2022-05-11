use
Camp_Manager;

CREATE TABLE `camper`
(
    `camper_id`     INT NOT NULL AUTO_INCREMENT,
    `camper_fname`  VARCHAR(32),
    `camper_lname`  VARCHAR(32),
    `camper_age`    INT(3),
    `camper_dob`    VARCHAR(10),
    `camper_docs`   CHAR(2),
    `session_month` VARCHAR(10),
    `camper_gender` VARCHAR(7),
    PRIMARY KEY (`camper_id`)
) ENGINE=INNODB;

CREATE TABLE `bunkhouse`
(
    `bunk_id`       INT NOT NULL,
    `camper_fname`  VARCHAR (32),
    `camper_lname`  VARCHAR (32),
    `camper_gender` VARCHAR (10),
    PRIMARY KEY (`bunk_id`)
)  ENGINE=INNODB;

CREATE TABLE `tribe` (
                         `tribe_name` CHAR,
                         `camper_fname` VARCHAR (32),
                         `camper_lname` VARCHAR (32),
                         `session_month` VARCHAR (12),
                         `special_req` VARCHAR (255),
                         `camper_age` INT,
                         PRIMARY KEY (`tribe_name`)
) ENGINE=INNODB;