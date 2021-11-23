package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.University;

import java.util.List;

@Repository
public class InstituteDaoImpl extends AbstractDao<Institute, Long> {

    InstituteDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Institute.class);
    }

    public List<Institute> findAllByUniversityId(Long universityId) {
        return getAllFromParent(universityId, University.class);
    }
}
