CREATE OR REPLACE PROCEDURE addUser(role_name text default 'ROLE_USER', username varchar(127), name varchar(63),
                                    surname varchar(63), vk_link varchar(255), email text, password text)
    returns null on null input
AS
$$
declare
    user_id       bigint;
    role_id       bigint;
    user__role_id bigint;
begin
    SELECT id
    into user_id
    from "user" u
    where u.email = addUser.email;

    SELECT id
    into role_id
    from role r
    where r.name = addUser.role_name;

    if user_id is null then
        insert into "user" (username, name, surname, vk_link, email, password, enabled)
        values (addUser.username, addUser.name, addUser.surname, addUser.vk_link, addUser.email, addUser.password, true)
        returning id into user_id;
    end if;

    if role_id is null then
        insert into role (name)
        values (addUser.role_name)
        returning id into role_id;
    end if;

    select id into user__role_id from user__role where user_id = user_id and role_id = role_id;

    if user__role_id is null then
        insert into user__role (role_id, user_id)
        values (role_id, user_id)
        returning id into user__role_id;
    end if;

    return user_id;
end;
$$ LANGUAGE 'plpgsql';


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
--     from "user" u
--     where u.email = addUser.email;
--
--     SELECT id
--     into role_id
--     from role r
--     where r.name = addUser.role_name;
--
--     if user_id is null then
--         insert into "user" (username, name, surname, vk_link, email, password, enabled)
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




