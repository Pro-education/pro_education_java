-- 1 имя группы и количество пользователей (group)
SELECT g.name, count(u.id)
FROM "group" as g
         join group__user gu on g.id = gu.group_id
         join "user" u on u.id = gu.users_id
GROUP BY g.name
order by 1;

-- 2 имя группы в которых пользователи сделали все задачи (exist, inner query)
SELECT g.name
FROM "group" as g
where not exists(
        select u.id
        from individual_task it
                 join "user" u on u.id = it.user_id
                 join group__user gu on g.id = gu.group_id
        where u.id = gu.users_id
          and not it.is_solved
    );

-- 3 найти количество задачи не решенные у пользователя в группах (where)
select u.id, username, count(mt)
from "user" u
         join individual_task it on u.id = it.user_id
         join main_task mt on mt.id = it.main_task_id
         join main_homework mh on mt.main_homework_id = mh.id
where u.id = 1
  and not it.is_solved
group by u.id, username;

-- 4 имя группы в который все пользователи выше 4 ранга
select g.name
from "group" g
where not exists(
        select u.id
        from group__user gu
                 join "user" u on u.id = gu.users_id
        where g.id = gu.group_id
          and u.rating_sum / u.rating_count < 4
    );

-- 5 университет и институты в которых рейтинг больше 3 (union)
select short_name,
       (
           select cast(sum(r.rating) as float4) / count(*)
           from review as r
           where u.id = r.table_id
             and table_name = 'university'
       ) as rating
from university as u
where 3 < (
    select cast(sum(r.rating) as float4) / count(*)
    from review as r
    where u.id = r.table_id
      and table_name = 'university'
)
union all
select short_name,
       (
           select cast(sum(r.rating) as float4) / count(*)
           from review as r
           where u.id = r.table_id
             and table_name = 'institute'
       ) as rating
from institute as u
where 3 < (
    select cast(sum(r.rating) as float4) / count(*)
    from review as r
    where u.id = r.table_id
      and table_name = 'institute'
);

-- 6 среднее значение которое ставит пользователь (order)
select u.id, username, cast(sum(r.rating) as float4) / count(r.id) as sred
from review as r
         join "user" u on u.id = r.user_id
group by u.id, username
order by 3 desc;

-- 7 первые 10 направления по рейтингу (limit having order)
select r.table_name, r.table_id, cast(sum(r.rating) as float4) / count(r.id) as sred
from review as r
where r.table_name like 'direction'
group by r.table_name, r.table_id
having cast(sum(r.rating) as float4) / count(r.id) > 3
order by sred desc
limit 10;

-- 8 есть ли у студента свои домашние задания (1 or 0) (case exist)
SELECT CASE
           WHEN EXISTS(
                   SELECT *
                   FROM "user"
                            inner join individual_task it on "user".id = it.user_id
                   WHERE "user".id = 1)
               THEN CAST(1 AS bool)
           ELSE CAST(0 AS bool) END;


-- 9 пользователи у которые написали отзывы (in inner)
select username
from "user"
where id in (
    select user_id
    from review
);

-- 10 показать все дискуссии во временном порядке, сгруппированные по отзывам
select review.id, d.text, d.created_time
from review
         join discussion d on review.id = d.review_id
group by review.id, review.text, d.text, d.created_time
order by d.created_time desc;

-- 11 объекты на которые есть отзывы (distinct)
select DISTINCT on (r.table_name, r.table_id) r.table_name, r.table_id
from review as r
group by r.table_name, r.table_id;

select "group".name, review.text
from "group"
         left join review on review.table_name = 'group' and review.table_id = "group".id



