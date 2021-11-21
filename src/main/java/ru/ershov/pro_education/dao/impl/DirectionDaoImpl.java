package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Direction;

@Repository
public class DirectionDaoImpl extends AbstractDao<Direction, Long> {

    public DirectionDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Direction.class);
    }
}
