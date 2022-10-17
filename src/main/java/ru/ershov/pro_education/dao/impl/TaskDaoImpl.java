package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Homework;
import ru.ershov.pro_education.entity.Task;

import java.util.List;

@Repository
public class TaskDaoImpl extends AbstractDao<Task, Long> {
    TaskDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Task.class);
    }

    public List<Task> findAllByHomeworkId(Long homeworkId) {
        return getAllFromParent(homeworkId, Homework.class);
    }
}
