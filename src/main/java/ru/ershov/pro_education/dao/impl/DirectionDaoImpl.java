package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Department;
import ru.ershov.pro_education.entity.Direction;

import java.util.List;

@Repository
public class DirectionDaoImpl extends AbstractDao<Direction, Long> {

    DirectionDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Direction.class);
    }

    public List<Direction> findAllByDepartmentId(Long departmentId){
        return getAllFromParent(departmentId, Department.class);
    }
}
