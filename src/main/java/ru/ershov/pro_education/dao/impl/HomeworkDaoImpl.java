package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Homework;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.entity.Team;

import java.util.List;

@Repository
public class HomeworkDaoImpl extends AbstractDao<Homework, Long> {
    HomeworkDaoImpl(
            NamedParameterJdbcTemplate jdbcTemplate
    ) {
        super(jdbcTemplate, Homework.class);
    }

    public List<Homework> findAllByTeamId(Long teamId) {
        return getAllFromParent(teamId, Team.class);
    }

    public void addMyHomework(Long homeworkId, Long personId) {
        jdbcTemplate.getJdbcTemplate().update(
                "call public.addPersonalTask (?, ?, ?)", homeworkId, personId, false);
    }
}
