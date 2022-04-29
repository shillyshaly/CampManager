## TEST AREA ##
use Camp_Manager;
show tables;
drop tables `female_bunk`, `male_bunk`;
drop tables `camper`;
drop tables `session`;
select * from camper;
select * from session;
select * from tribe;
select * from camper where session_month = "august";
select count(*) from camper where session_month = "august";

insert into camper (camper_fname, camper_lname, camper_age, camper_house, camper_tribe, camper_docs, session_month) values ("vicky", "bell", 37, "b7", "A", "y", "august");
insert into session values ("august");
select count(*) from tribe;

alter table tribe add camper_fname varchar(32), add camper_lname varchar(32), add gender char(8);
alter table tribe add camper_gender varchar(8), add session_month varchar(12), add special_req varchar(255);