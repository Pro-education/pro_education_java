CREATE TABLE university
(
    id           BIGINT PRIMARY KEY,
    short_name   text,
    full_name    text,
    vk_link      text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    owner        bigint,
    check_status text,
    approval_id  bigint
);

CREATE TABLE institute
(
    id            BIGINT PRIMARY KEY,
    short_name    text,
    full_name     text,
    university_id bigint,
    vk_link       text,
    created_time  timestamp default (current_timestamp),
    updated_time  timestamp,
    check_status  text,
    approval_id  bigint
);

CREATE TABLE department
(
    id           BIGINT PRIMARY KEY,
    short_name   text,
    full_name    text,
    vk_link      text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE direction
(
    id            BIGINT PRIMARY KEY,
    department_id bigint,
    number        text,
    name          text,
    vk_link       text,
    created_time  timestamp default (current_timestamp),
    updated_time  timestamp,
    check_status  text,
    approval_id  bigint
);

CREATE TABLE institute__department
(
    id            BIGSERIAL PRIMARY KEY,
    department_id bigint,
    institute_id  bigint,
    created_time  timestamp default (current_timestamp),
    updated_time  timestamp,
    check_status  text,
    approval_id  bigint,
    UNIQUE (department_id, institute_id)
);

CREATE TABLE team
(
    id           BIGINT PRIMARY KEY,
    headman_id   bigint,
    direction_id bigint,
    name         text,
    vk_link      text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE person
(
    id           BIGSERIAL PRIMARY KEY,
    username     text,
    name         text,
    surname      text,
    vk_link      text,
    email        text,
    password     text,
    enabled      boolean,
    rating_sum   bigint,
    rating_count bigint,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE role
(
    id           BIGSERIAL PRIMARY KEY,
    name         text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE person__role
(
    id           BIGSERIAL PRIMARY KEY,
    role_id      bigint,
    person_id    bigint,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint,
    UNIQUE (role_id, person_id)
);

CREATE TABLE team__person
(
    id           BIGSERIAL PRIMARY KEY,
    team_id      bigint,
    person_id    bigint,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint,
    UNIQUE (team_id, person_id)
);

CREATE TABLE review
(
    id           BIGSERIAL PRIMARY KEY,
    person_id    bigint,
    status_id    bigint,
    table_id     bigint,
    table_name   text,
    text         text,
    rating       int,
    created_time timestamp DEFAULT (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE tag
(
    id           BIGSERIAL PRIMARY KEY,
    text         text,
    color        text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE review__tag
(
    id           BIGSERIAL PRIMARY KEY,
    review_id    bigint,
    tag_id       bigint,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint,
    UNIQUE (review_id, tag_id)
);

CREATE TABLE homework
(
    id           BIGSERIAL PRIMARY KEY,
    team_id      bigint,
    name         text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE task
(
    id           BIGSERIAL PRIMARY KEY,
    text         text,
    subject_id   bigint,
    homework_id  bigint,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE personal_task
(
    id           BIGSERIAL PRIMARY KEY,
    task_id      bigint,
    person_id    bigint,
    is_solved    boolean,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE status
(
    id           BIGSERIAL PRIMARY KEY,
    name         text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE discussion
(
    id           BIGSERIAL PRIMARY KEY,
    review_id    bigint,
    person_id    bigint,
    text         text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

CREATE TABLE subject
(
    id           BIGINT PRIMARY KEY,
    name         text,
    direction_id bigint,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    check_status text,
    approval_id  bigint
);

create sequence table_with_reviews_id_seq;

alter TABLE department
    alter column id set default nextval('table_with_reviews_id_seq');

alter TABLE direction
    alter column id set default nextval('table_with_reviews_id_seq');

alter TABLE institute
    alter column id set default nextval('table_with_reviews_id_seq');

alter TABLE subject
    alter column id set default nextval('table_with_reviews_id_seq');

alter TABLE university
    alter column id set default nextval('table_with_reviews_id_seq');

alter TABLE team
    alter column id set default nextval('table_with_reviews_id_seq');

ALTER TABLE university
    ADD FOREIGN KEY (owner) REFERENCES person (id);

ALTER TABLE institute
    ADD FOREIGN KEY (university_id) REFERENCES university (id);

ALTER TABLE direction
    ADD FOREIGN KEY (department_id) REFERENCES department (id);

ALTER TABLE institute__department
    ADD FOREIGN KEY (department_id) REFERENCES department (id);

ALTER TABLE institute__department
    ADD FOREIGN KEY (institute_id) REFERENCES institute (id);

ALTER TABLE team
    ADD FOREIGN KEY (headman_id) REFERENCES person (id);

ALTER TABLE team
    ADD FOREIGN KEY (direction_id) REFERENCES direction (id);

ALTER TABLE person__role
    ADD FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE person__role
    ADD FOREIGN KEY (person_id) REFERENCES person (id);

ALTER TABLE team__person
    ADD FOREIGN KEY (team_id) REFERENCES team (id);

ALTER TABLE team__person
    ADD FOREIGN KEY (person_id) REFERENCES person (id);

ALTER TABLE review
    ADD FOREIGN KEY (person_id) REFERENCES person (id);


ALTER TABLE review
    ADD FOREIGN KEY (status_id) REFERENCES status (id);

ALTER TABLE review__tag
    ADD FOREIGN KEY (review_id) REFERENCES review (id);

ALTER TABLE review__tag
    ADD FOREIGN KEY (tag_id) REFERENCES tag (id);

ALTER TABLE homework
    ADD FOREIGN KEY (team_id) REFERENCES team (id);

ALTER TABLE task
    ADD FOREIGN KEY (subject_id) REFERENCES subject (id);

ALTER TABLE task
    ADD FOREIGN KEY (homework_id) REFERENCES homework (id);

ALTER TABLE personal_task
    ADD FOREIGN KEY (task_id) REFERENCES task (id);

ALTER TABLE personal_task
    ADD FOREIGN KEY (person_id) REFERENCES person (id);

ALTER TABLE discussion
    ADD FOREIGN KEY (review_id) REFERENCES review (id);

ALTER TABLE discussion
    ADD FOREIGN KEY (person_id) REFERENCES person (id);

ALTER TABLE subject
    ADD FOREIGN KEY (direction_id) REFERENCES direction (id);

INSERT INTO person (email, password, enabled)
values ('string', 'password', true);

INSERT into role(name)
values ('ROLE_USER');
INSERT into role(name)
values ('ROLE_ADMIN');

INSERT into person__role (role_id, person_id)
values (1, 1);

INSERT INTO person (email, password, enabled)
values ('string1', 'stringstring', true);

INSERT into person__role (role_id, person_id)
values (1, 2);



INSERT INTO person (email, password, enabled)
values ('admin', 'adminadmin', true);



INSERT into person__role (role_id, person_id)
values (2, 3);
