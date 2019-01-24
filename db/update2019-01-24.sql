alter table user add mail varchar(100) not Null;
alter table user add active_status varchar(2) not Null;
alter table user alter column active_status set default '0';