package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Department;

@Repository
public class DepartmentDaoImpl extends AbstractDao<Department, Long> {

    DepartmentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Department.class);
    }


}
