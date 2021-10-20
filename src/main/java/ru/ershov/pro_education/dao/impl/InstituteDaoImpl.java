package ru.ershov.pro_education.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Institute;

@Repository
public class InstituteDaoImpl extends AbstractDao<Institute, Long> {

    private final String selectAllInstitutesFromParent = getBaseSqlQuery() + "WHERE university_id = :parentId";

    @Autowired
    InstituteDaoImpl(
            NamedParameterJdbcTemplate jdbcTemplate
    ) {
        super(jdbcTemplate, Institute.class);
    }
}
