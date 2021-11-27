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

CREATE TRIGGER update_time_group
    BEFORE UPDATE
    ON "group"
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_group__user
    BEFORE UPDATE
    ON group__user
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
    ON review_tag
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

CREATE TRIGGER update_time_user
    BEFORE UPDATE
    ON "user"
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();

CREATE TRIGGER update_time_user__role
    BEFORE UPDATE
    ON user__role
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_time_column();
