create database dockerLogin;
use dockerLogin;

create table member(
    memberId int not null primary key auto_increment comment '회원번호',
    id varchar(40) not null comment '회원 아이디',
    pwd varchar(10) not null comment '회원 비밀번호'
);

select * from member;