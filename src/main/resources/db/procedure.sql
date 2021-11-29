CREATE OR REPLACE PROCEDURE addPerson(role_name text default 'ROLE_USER', username varchar(127), name varchar(63),
                                      surname varchar(63), vk_link varchar(255), email text, password text)
    returns null on null input
AS
$$
declare
    person_id       bigint;
    role_id         bigint;
    person__role_id bigint;
begin
    SELECT id
    into person_id
    from person p
    where p.email = addPerson.email;

    SELECT id
    into role_id
    from role r
    where r.name = addPerson.role_name;

    if person_id is null then
        insert into person (username, name, surname, vk_link, email, password, enabled)
        values (addPerson.username, addPerson.name, addPerson.surname, addPerson.vk_link, addPerson.email,
                addPerson.password, true)
        returning id into person_id;
    end if;

    if role_id is null then
        insert into role (name)
        values (addPerson.role_name)
        returning id into role_id;
    end if;

    select id into person__role_id from person__role where person_id = person_id and role_id = role_id;

    if person__role_id is null then
        insert into person__role (role_id, person_id)
        values (role_id, person_id)
        returning id into person__role_id;
    end if;

    return person_id;
end;
$$ LANGUAGE 'plpgsql';

-- CREATE OR REPLACE PROCEDURE add_to_personal_task()
-- AS
-- $personal_task_trigger$
-- BEGIN
--     SELECT DISTINCT gu.users_id AS user_id_s, t.id AS task_id_s
--     FROM task t, homework h, "group" g, group__user gu, personal_task pt
--     WHERE t.homework_id = h.id
--       AND h.group_id = g.id
--       AND g.id = gu.group_id
--     ORDER BY gu.users_id, task_id;
--     INSERT INTO personal_task (task_id, user_id, is_solved)
--     VALUES (task_id_s)
--     --             SELECT DISTINCT gu.users_id, t.id AS task_id
-- --             FROM task t, homework h, "group" g, group__user gu, personal_task pt
-- --             WHERE t.homework_id = h.id
-- --             AND h.group_id = g.id
-- --             AND g.id = gu.group_id
-- --             ORDER BY gu.users_id, task_id;
--     INSERT INTO personal_task (is_solved) VALUES (false);
--     RETURN NULL;
-- end;
-- $personal_task_trigger$ LANGUAGE plpgsql;

-- CREATE OR REPLACE PROCEDURE addUTask(role_name text default 'ROLE_USER', username varchar(127), name varchar(63),
--                                     surname varchar(63), vk_link varchar(255), email text, password text)
--     returns null on null input
-- AS
-- $$
-- declare
--     user_id       bigint;
--     role_id       bigint;
--     user__role_id bigint;
-- begin
--     SELECT id
--     into user_id
--     from "person" u
--     where u.email = addUser.email;
--
--     SELECT id
--     into role_id
--     from role r
--     where r.name = addUser.role_name;
--
--     if user_id is null then
--         insert into "person" (username, name, surname, vk_link, email, password, enabled)
--         values (addUser.username, addUser.name, addUser.surname, addUser.vk_link, addUser.email, addUser.password, true)
--         returning id into user_id;
--     end if;
--
--     if role_id is null then
--         insert into role (name)
--         values (addUser.role_name)
--         returning id into role_id;
--     end if;
--
--     select id into user__role_id from user__role where user_id = user_id and role_id = role_id;
--
--     if user__role_id is null then
--         insert into user__role (role_id, user_id)
--         values (role_id, user_id)
--         returning id into user__role_id;
--     end if;
--
--     return user_id;
-- end;
-- $$ LANGUAGE 'plpgsql';




