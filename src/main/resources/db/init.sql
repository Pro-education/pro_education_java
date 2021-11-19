CREATE TABLE "university"
(
    "id"         BIGSERIAL PRIMARY KEY,
    "short_name" varchar(127),
    "full_name"  varchar(255),
    "reviews_id" bigint,
    "vk_link"    varchar(255)
);

CREATE TABLE "institute"
(
    "id"            BIGSERIAL PRIMARY KEY,
    "short_name"    varchar(127),
    "full_name"     varchar(255),
    "university_id" bigint,
    "vk_link"       varchar(255)
);

CREATE TABLE "department"
(
    "id"         BIGSERIAL PRIMARY KEY,
    "short_name" varchar(127),
    "full_name"  varchar(255),
    "vk_link"    varchar(255)
);

CREATE TABLE "direction"
(
    "id"             BIGSERIAL PRIMARY KEY,
    "departament_id" bigint,
    "number"         varchar(255),
    "name"           varchar(255),
    "reviews_id"     bigint,
    "vk_link"        varchar(255)
);

CREATE TABLE "institute_departament"
(
    "id"             BIGSERIAL PRIMARY KEY,
    "departament_id" bigint,
    "institute_id"   bigint
);

CREATE TABLE "groups"
(
    "id"            BIGSERIAL PRIMARY KEY,
    "headman"       bigint,
    "direction_id"  bigint,
    "vk_link"       varchar(255),
    "reviews_id"    bigint,
    "raiting_sum"   bigint,
    "raiting_count" bigint
);

CREATE TABLE "users"
(
    "id"            BIGSERIAL PRIMARY KEY,
    "usename"       varchar(127),
    "name"          varchar(63),
    "suname"        varchar(63),
    "vk_link"       varchar(255),
    "raiting_sum"   bigint,
    "raiting_count" bigint
);

CREATE TABLE "role"
(
    "id"   BIGSERIAL PRIMARY KEY,
    "name" varchar(31)
);

CREATE TABLE "users__role"
(
    "id"      BIGSERIAL PRIMARY KEY,
    "role_id" bigint,
    "user_id" bigint
);

CREATE TABLE "groups_users"
(
    "id"       BIGSERIAL PRIMARY KEY,
    "group_id" bigint,
    "users_id" bigint
);

CREATE TABLE "reviews"
(
    "id"          BIGSERIAL PRIMARY KEY,
    "user_id"     bigint,
    "status_id"   bigint,
    "link_id"     bigint,
    "link_name"   int,
    "text"        text,
    "reiting"     int,
    "create_time" timestamp DEFAULT (current_timestamp)
);

CREATE TABLE "tag"
(
    "id"    BIGSERIAL PRIMARY KEY,
    "text"  varchar(63),
    "color" varchar(32)
);

CREATE TABLE "reviews_tag"
(
    "id"        BIGSERIAL PRIMARY KEY,
    "review_id" bigint,
    "tag_id"    bigint
);

CREATE TABLE "main_homeworks"
(
    "id"       BIGSERIAL PRIMARY KEY,
    "group_id" bigint,
    "name"     varchar(255)
);

CREATE TABLE "main_tasks"
(
    "id"               BIGSERIAL PRIMARY KEY,
    "text"             text,
    "main_homework_id" bigint
);

CREATE TABLE "individual_tasks"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "main_task_id" bigint,
    "user_id"      bigint,
    "is_solved"    boolean
);

CREATE TABLE "status"
(
    "id"     BIGSERIAL PRIMARY KEY,
    "status" text
);

CREATE TABLE "discussions"
(
    "id"        BIGSERIAL PRIMARY KEY,
    "review_id" bigint,
    "user_id"   bigint,
    "text"      text
);

ALTER TABLE "institute"
    ADD FOREIGN KEY ("university_id") REFERENCES "university" ("id");

ALTER TABLE "direction"
    ADD FOREIGN KEY ("departament_id") REFERENCES "department" ("id");

ALTER TABLE "institute_departament"
    ADD FOREIGN KEY ("departament_id") REFERENCES "department" ("id");

ALTER TABLE "institute_departament"
    ADD FOREIGN KEY ("institute_id") REFERENCES "institute" ("id");

ALTER TABLE "groups"
    ADD FOREIGN KEY ("headman") REFERENCES "users" ("id");

ALTER TABLE "groups"
    ADD FOREIGN KEY ("direction_id") REFERENCES "direction" ("id");

ALTER TABLE "users__role"
    ADD FOREIGN KEY ("role_id") REFERENCES "role" ("id");

ALTER TABLE "users__role"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "groups_users"
    ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("id");

ALTER TABLE "groups_users"
    ADD FOREIGN KEY ("users_id") REFERENCES "users" ("id");

ALTER TABLE "reviews"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "reviews"
    ADD FOREIGN KEY ("status_id") REFERENCES "status" ("id");

ALTER TABLE "reviews_tag"
    ADD FOREIGN KEY ("review_id") REFERENCES "reviews" ("id");

ALTER TABLE "reviews_tag"
    ADD FOREIGN KEY ("tag_id") REFERENCES "tag" ("id");

ALTER TABLE "main_homeworks"
    ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("id");

ALTER TABLE "main_tasks"
    ADD FOREIGN KEY ("main_homework_id") REFERENCES "main_homeworks" ("id");

ALTER TABLE "individual_tasks"
    ADD FOREIGN KEY ("main_task_id") REFERENCES "main_tasks" ("id");

ALTER TABLE "individual_tasks"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "discussions"
    ADD FOREIGN KEY ("review_id") REFERENCES "reviews" ("id");

ALTER TABLE "discussions"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");
