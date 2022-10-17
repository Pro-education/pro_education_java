CREATE OR REPLACE PROCEDURE public.addPerson(role_name text, username varchar(127), name varchar(63),
                                      surname varchar(63), vk_link varchar(255), email text, password text)
LANGUAGE plpgsql
AS
$$
declare
    _person_id       bigint;
    _role_id         bigint;
    _person__role_id bigint;
begin
    SELECT id
    into _person_id
    from person p
    where p.email = addPerson.email;

    SELECT id
    into _role_id
    from role r
    where r.name = addPerson.role_name;

    if _person_id is null then
        insert into person (username, name, surname, vk_link, email, password, enabled)
        values (addPerson.username, addPerson.name, addPerson.surname, addPerson.vk_link, addPerson.email,
                addPerson.password, true)
        returning id into _person_id;
    end if;

    if _role_id is null then
        insert into role (name)
        values (addPerson.role_name)
        returning id into _role_id;
    end if;

    select id
    into _person__role_id
    from person__role pr
    where pr.person_id = _person_id
      and pr.role_id = _role_id;

    if _person__role_id is null then
        insert into person__role (role_id, person_id)
        values (_role_id, _person_id)
        returning id into _person__role_id;
    end if;
end;
$$;

CREATE OR REPLACE PROCEDURE public.addPersonalTask(in_homework_id bigint, in_person_id bigint, in_is_solved boolean)
AS
    $$
    declare
        _ids_array INTEGER[];
        _id INTEGER;
    begin

        if in_is_solved is null THEN
            raise exception 'Solution must have status!';
        end if;

        if (SELECT id
        FROM homework h
        WHERE h.id = in_homework_id) is null then
            raise exception 'Homework with id = % not found', in_homework_id;
        end if;

        if (SELECT id
            FROM person p
            WHERE p.id = in_person_id) is null then
            raise exception 'Person with id = % not found', in_person_id;
        end if;

        SELECT ARRAY(SELECT id
                    FROM task t
                    WHERE t.homework_id = in_homework_id) INTO _ids_array;
        FOREACH _id IN ARRAY _ids_array
            LOOP
                INSERT INTO personal_task (task_id, person_id, is_solved)
                VALUES (_id, in_person_id, in_is_solved);
            END LOOP;

    end;
$$ LANGUAGE 'plpgsql';

-- CALL addPerson('ROLE_USER', 'username3', 'name3', 'surname3', 'https://vk.com/3', 'email3', 'password3');
-- CALL addPersonalTask(null, 2, false);




