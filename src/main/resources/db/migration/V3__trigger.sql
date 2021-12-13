CREATE OR REPLACE FUNCTION update_updated_time_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_time = now();
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER update_time_department
    BEFORE UPDATE
    ON department
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_direction
    BEFORE UPDATE
    ON direction
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_discussion
    BEFORE UPDATE
    ON discussion
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_team
    BEFORE UPDATE
    ON team
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_team__person
    BEFORE UPDATE
    ON team__person
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_individual_task
    BEFORE UPDATE
    ON personal_task
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_institute
    BEFORE UPDATE
    ON institute
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_institute__department
    BEFORE UPDATE
    ON institute__department
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_main_homework
    BEFORE UPDATE
    ON homework
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_main_task
    BEFORE UPDATE
    ON task
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_review
    BEFORE UPDATE
    ON review
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_review_tag
    BEFORE UPDATE
    ON review__tag
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_role
    BEFORE UPDATE
    ON role
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_status
    BEFORE UPDATE
    ON status
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_subject
    BEFORE UPDATE
    ON subject
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_tag
    BEFORE UPDATE
    ON tag
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_university
    BEFORE UPDATE
    ON university
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_person
    BEFORE UPDATE
    ON person
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_person__role
    BEFORE UPDATE
    ON person__role
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();


-- CREATE TRIGGER add_personal_task
--     BEFORE UPDATE OR INSERT
--     ON task
--     FOR EACH ROW

-- CREATE OR REPLACE VIEW personal_task AS
--     SELECT t.id, gu.users_id
--         FROM task t, homework h, group g, group__user gu, personal_task pt
--         WHERE t.homework_id = h.id
--         AND h.group_id = g.id
--         AND g.id = gu.group_id


--
-- CREATE OR REPLACE TRIGGER personal_task_insert_trigger
--     AFTER INSERT ON task
--     FOR EACH ROW
-- DECLARE
-- BEGIN
--     CASE
--         WHEN INSERTING THEN
--             INSERT INTO personal_task (task_id, user_id)
--             SELECT :NEW.id, gu.users_id
--                 FROM group__user gu
--                     INNER JOIN group g
--                             ON g.id = gu.group_id
--                     INNER JOIN homework h
--                             ON h.group_id = g.id
--             WHERE h.id = :NEW.homework_id
