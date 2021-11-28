INSERT INTO "user" (username, name, surname, vk_link, email, password, enabled, rating_sum, rating_count)
VALUES ('username1', 'name1', 'surname1', 'https://vk.com/1', 'email1', 'password1', true, 5, 1),
       ('username2', 'name2', 'surname2', 'https://vk.com/2', 'email2', 'password2', true, 4, 1);

INSERT INTO role (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT INTO user__role (role_id, user_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO university (id, short_name, full_name, vk_link)
VALUES (1, 'СПбПУ1', 'Политех1', 'https://vk.com/1'),
       (2, 'СПбПУ2', 'Политех2', 'https://vk.com/2');

INSERT INTO institute (id, short_name, full_name, university_id, vk_link)
VALUES (1, 'ИКНТ1', 'Институт компьютеров1', 1, 'https://vk.com/1'),
       (2, 'ИКНТ2', 'Институт компьютеров2', 2, 'https://vk.com/2'),
       (3, 'ИКНТ3', 'Институт компьютеров3', 1, 'https://vk.com/3');

INSERT INTO department (id, short_name, full_name, vk_link)
VALUES (1, 'деп1', 'департамент1', 'https://vk.com/1'),
       (2, 'деп2', 'департамент2', 'https://vk.com/2'),
       (3, 'деп3', 'департамент3', 'https://vk.com/3');

INSERT INTO institute__department (department_id, institute_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (3, 3);

INSERT INTO direction (id, department_id, number, name, vk_link)
VALUES (1, 1, '1/1', 'направление1', 'https://vk.com/1'),
       (2, 2, '1/2', 'направление2', 'https://vk.com/2'),
       (3, 3, '1/3', 'направление3', 'https://vk.com/3'),
       (4, 1, '1/4', 'направление4', 'https://vk.com/4');

INSERT INTO subject (id, name, direction_id)
VALUES (1, 'предмет1', 1),
       (2, 'предмет2', 1),
       (3, 'предмет3', 2);

INSERT INTO "group" (id, headman, direction_id, name, vk_link)
VALUES (1, 1, 1, 'group1', 'https://vk.com/1'),
       (2, 2, 1, 'group2', 'https://vk.com/2'),
       (3, 1, 2, 'group3', 'https://vk.com/3');

INSERT INTO group__user (group_id, users_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 2);

INSERT INTO homework (id, group_id, name)
VALUES (1, 1, 'homeworks1'),
       (2, 2, 'homeworks2'),
       (3, 1, 'homeworks3');

INSERT INTO task (id, text, subject_id, main_homework_id)
VALUES (1, 'task1', 1, 1),
       (2, 'task2', 2, 1),
       (3, 'task3', 3, 2),
       (4, 'task4', 1, 3);

INSERT INTO personal_task (id, main_task_id, user_id, is_solved)
VALUES (1, 1, 1, true),
       (2, 2, 1, true),
       (3, 3, 1, false),
       (4, 3, 2, false),
       (5, 4, 2, true);

INSERT INTO status (id, status)
VALUES (1, 'на проверке'),
       (2, 'прошел валидацию'),
       (3, 'отказан');

INSERT INTO review (id, user_id, status_id, table_id, table_name, text, rating)
VALUES (1, 1, 1, 1, 'direction', 'отзыв1', 4),
       (2, 2, 2, 1, 'direction', 'отзыв2', 5),
       (3, 1, 1, 2, 'direction', 'отзыв3', 3),
       (4, 2, 3, 2, 'direction', 'отзыв4', 5),
       (5, 2, 2, 1, 'institute', 'отзыв4', 2),
       (6, 1, 2, 1, 'institute', 'отзыв1', 3);

INSERT INTO tag (id, text, color)
VALUES (1, 'tag1', 'color1'),
       (2, 'tag2', 'color2');

INSERT INTO review_tag (review_id, tag_id)
VALUES (1, 1),
       (2, 2),
       (1, 2);

INSERT INTO discussion (id, review_id, user_id, text)
VALUES (1, 1, 1, 'discussions1'),
       (2, 1, 2, 'discussions2'),
       (3, 2, 1, 'discussions3'),
       (4, 2, 2, 'discussions4');


