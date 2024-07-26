--liquibase formatted sql

--changeset akuznetsov:1
insert into company(name)
values ('Google'),
       ('Meta'),
       ('Yandex');


insert into company_locales(company_id, lang, description)
VALUES ((select id from company where name = 'Google'), 'en', 'Google description'),
       ((select id from company where name = 'Google'), 'ru', 'Google описание'),
       ((select id from company where name = 'Meta'), 'en', 'Meta description'),
       ((select id from company where name = 'Meta'), 'ru', 'Meta описание'),
       ((select id from company where name = 'Yandex'), 'en', 'Yandex description'),
       ((select id from company where name = 'Yandex'), 'ru', 'Yandex описание');


INSERT INTO users (username, birth_date, firstname, lastname, role, company_id)
values ('tolik228@gmail.com', '2001-3-21', 'Anatoly', 'Wasserman', 'ADMIN',
        (select c.id from company c where c.id = 1)),
       ('andrei@gmail.com', '2003-2-15', 'Andrei', 'Hawk', 'ADMIN',
        (select c.id from company c where c.id = 2)),
       ('Evgeny@gmail.com', '2002-3-16', 'Evgeny', 'Gromov', 'ADMIN',
        (select c.id from company c where c.id = 3));