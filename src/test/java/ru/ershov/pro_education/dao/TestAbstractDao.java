package ru.ershov.pro_education.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.service.clazz.TestAbstractEntity;

@Repository
public class TestAbstractDao extends AbstractDao<TestAbstractEntity, Long> {
    @Autowired
    protected TestAbstractDao(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TestAbstractEntity.class);
    }
}
