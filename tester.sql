## TEST AREA ##
use Camp_Manager;
show tables;

select * from camper;
select * from session;
select * from tribe;
select * from bunkhouse;
select * from camper where session_month = "august";
select count(*) from camper where session_month = "august";

insert into camper (camper_fname, camper_lname, camper_age, camper_house, camper_tribe, camper_docs, session_month) values ("vicky", "bell", 37, "b7", "A", "y", "august");
insert into session values ("august");
select count(*) from tribe;

alter table tribe add camper_fname varchar(32), add camper_lname varchar(32), add gender char(8);
alter table tribe add camper_gender varchar(8), add session_month varchar(12), add special_req varchar(255);

update tribe set tribe_name = "a" where tribe_name = "tribe a";
update camper set camper_lname = "stotch" where camper_lname = "stoch";

select avg(camper_age) as age from tribe where tribe_name = "c";
select * from camper where camper_fname = 'jamie';

delete from camper where camper_fname = "testish";

select count(1) as test from camper where camper_fname = 'testish';
select count(*) as camper from camper where camper_gender = 'male' and session_month = 'august';
select count(*) as camper from camper where session_month = 'july';


select camper_fname, camper_lname
from camper 
where row(camper.camper_fname, camper.camper_lname) not in
	(
    select camper_fname, camper_lname
    from tribe
    );

select camper_fname, camper_lname from tribe where camper_fname not in (select camper_fname from camper)
union
select camper_fname, camper_lname from camper where camper_fname not in (select camper_fname from tribe);