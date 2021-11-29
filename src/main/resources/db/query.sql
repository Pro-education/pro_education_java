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


-- SELECT gu.users_id
-- FROM group__user gu
-- INNER JOIN group g
--         ON g.id = gu.group_id
-- INNER JOIN homework h
--         ON h.group_id = g.id
-- INNER JOIN task t
--         ON t.homework_id = h.id