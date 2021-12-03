INSERT INTO person (username, name, surname, vk_link, email, password, enabled, rating_sum, rating_count)
VALUES ('username1', 'name1', 'surname1', 'https://vk.com/1', 'email1', 'password1', true, 5, 1),
       ('username2', 'name2', 'surname2', 'https://vk.com/2', 'email2', 'password2', true, 4, 1);

INSERT INTO role (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO person__role (role_id, person_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO university (short_name, full_name, vk_link)
VALUES ('СПбПУ1', 'Политех1', 'https://vk.com/1'),
       ('СПбПУ2', 'Политех2', 'https://vk.com/2');

INSERT INTO institute (short_name, full_name, university_id, vk_link)
VALUES ('ИКНТ1', 'Институт компьютеров1', 1, 'https://vk.com/1'),
       ('ИКНТ2', 'Институт компьютеров2', 2, 'https://vk.com/2'),
       ('ИКНТ3', 'Институт компьютеров3', 1, 'https://vk.com/3');

INSERT INTO department (short_name, full_name, vk_link)
VALUES ('деп1', 'департамент1', 'https://vk.com/1'),
       ('деп2', 'департамент2', 'https://vk.com/2'),
       ('деп3', 'департамент3', 'https://vk.com/3');

INSERT INTO institute__department (department_id, institute_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (3, 3);

INSERT INTO direction (department_id, number, name, vk_link)
VALUES (1, '1/1', 'направление1', 'https://vk.com/1'),
       (2, '1/2', 'направление2', 'https://vk.com/2'),
       (3, '1/3', 'направление3', 'https://vk.com/3'),
       (1, '1/4', 'направление4', 'https://vk.com/4');

INSERT INTO subject (name, direction_id)
VALUES ('предмет1', 1),
       ('предмет2', 1),
       ('предмет3', 2);

INSERT INTO team (headman_id, direction_id, name, vk_link)
VALUES (1, 1, 'group1', 'https://vk.com/1'),
       (2, 1, 'group2', 'https://vk.com/2'),
       (1, 2, 'group3', 'https://vk.com/3');

INSERT INTO team__person (team_id, person_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 2);

INSERT INTO homework (team_id, name)
VALUES (1, 'homeworks1'),
       (2, 'homeworks2'),
       (1, 'homeworks3');

INSERT INTO task (text, subject_id, homework_id)
VALUES ('task1', 1, 1),
       ('task2', 2, 1),
       ('task3', 3, 2),
       ('task4', 1, 3);

INSERT INTO personal_task (task_id, person_id, is_solved)
VALUES (1, 1, true),
       (2, 1, true),
       (3, 1, false),
       (3, 2, false),
       (4, 2, true);

INSERT INTO status (name)
VALUES ('на проверке'),
       ('прошел валидацию'),
       ('отказан');

INSERT INTO review (person_id, status_id, table_id, table_name, text, rating)
VALUES (1, 1, 1, 'direction', 'отзыв1', 4),
       (2, 2, 1, 'direction', 'отзыв2', 5),
       (1, 1, 2, 'direction', 'отзыв3', 3),
       (2, 3, 2, 'direction', 'отзыв4', 5),
       (2, 2, 1, 'institute', 'отзыв4', 2),
       (1, 2, 1, 'institute', 'отзыв1', 3);

INSERT INTO tag (text, color)
VALUES ('tag1', 'color1'),
       ('tag2', 'color2');

INSERT INTO review__tag (review_id, tag_id)
VALUES (1, 1),
       (2, 2),
       (1, 2);

INSERT INTO discussion (review_id, person_id, text)
VALUES (1, 1, 'discussions1'),
       (1, 2, 'discussions2'),
       (2, 1, 'discussions3'),
       (2, 2, 'discussions4');


