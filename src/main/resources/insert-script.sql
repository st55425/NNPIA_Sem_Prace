
insert into user(blocked, first_name, is_club_member, password, role, surname, username)
VALUES (false, 'Test', true, '$2a$10$wJV47DU.74BMjCox1u45s.6kFrwnnCVMzqOeZ9dXLuoOsMxthbEBa
', 'USER', 'User', 'user');

insert into user(blocked, first_name, is_club_member, password, role, surname, username)
VALUES (false, 'Test', true, '$2a$10$TWYzj9itHzj4KPFXg6LJ4.ykAtsFwT56k0wodOkRIgHSNwvmKvuAC
', 'STAFF', 'Staff', 'staff');

insert into user(blocked, first_name, is_club_member, password, role, surname, username)
VALUES (false, 'Test', true, '$2a$10$KzqnXd1Vk66jzDvqfaleHuGGWw5ZFkzw0s9H6V2irGx7kDQVtahyS
', 'ADMIN', 'Admin', 'admin');

insert into reservable_type(name, is_court) values ('tenisový kurt', true);

insert into reservable(available, name, reservable_type_id) VALUES (true, 'Centr kurt', 1);
insert into reservable(available, name, reservable_type_id) VALUES (true, 'Kurt 1', 1);

insert into reservation(time_from, time_to, reservable_id, user_id)
VALUES ('2021-6-10 13:00:00', '2021-6-10 14:00:00', 1, 1);
insert into reservation(time_from, time_to, reservable_id, user_id)
VALUES ('2021-6-11 14:00:00', '2021-6-10 16:00:00', 1, 1);
insert into reservation(time_from, time_to, reservable_id, user_id)
VALUES ('2021-6-10 10:00:00', '2021-6-10 12:00:00', 1, 1);
insert into reservation(time_from, time_to, reservable_id, user_id)
VALUES ('2021-6-9 10:00:00', '2021-6-9 12:00:00', 1, 1);