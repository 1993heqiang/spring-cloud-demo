create table PROPERTIES
(
    ID identity not null primary key,
    KEY varchar(256) not null,
    VALUE varchar(256) not null,
    APPLICATION varchar(256) not null,
    PROFILE varchar(256) not null,
    LABEL varchar(128) not null
);
insert into PROPERTIES(key, value, application, profile, label) values('first', 'hello', 'test', 'jdbc', 'master');
insert into PROPERTIES(key, value, application, profile, label) values('second', 'world', 'test', 'jdbc', 'master');
insert into PROPERTIES(key, value, application, profile, label) values('third', 'ok', 'test', 'jdbc', 'master');