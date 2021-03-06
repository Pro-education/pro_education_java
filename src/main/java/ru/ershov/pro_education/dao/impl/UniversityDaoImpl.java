package ru.ershov.pro_education.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.University;

@Repository
public class UniversityDaoImpl extends AbstractDao<University, Long> {

    UniversityDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, University.class);
    }

}
