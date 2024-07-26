--liquibase formatted sql

--changeset akuznetsov:1
alter table users
    drop column info;