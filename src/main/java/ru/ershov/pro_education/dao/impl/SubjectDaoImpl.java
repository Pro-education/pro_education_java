package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Subject;

@Repository
public class SubjectDaoImpl extends AbstractDao<Subject, Long> {
    SubjectDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Subject.class);
    }
}
