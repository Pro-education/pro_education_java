package ru.ershov.pro_education.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Authorities;

@Repository
public class AuthoritiesDaoImpl extends AbstractDao<Authorities, Long> {



    @Autowired
    protected AuthoritiesDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Authorities.class);
    }

}
