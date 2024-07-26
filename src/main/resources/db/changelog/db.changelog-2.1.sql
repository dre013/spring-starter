--liquibase formatted sql

--changeset akuznetsov:1
alter table users
    add column info varchar(128);