package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.PersonalTask;

@Repository
public class PersonalTaskDaoImpl extends AbstractDao<PersonalTask, Long> {
    PersonalTaskDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, PersonalTask.class);
    }
}
