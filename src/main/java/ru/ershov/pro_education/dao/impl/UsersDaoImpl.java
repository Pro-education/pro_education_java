package ru.ershov.pro_education.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UsersDaoImpl extends AbstractDao<User, Long> {

    private final String sql = this.getBaseSqlQuery() + " where email = :email";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    protected UsersDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, User.class);
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findByEmail(String email) {
        List<User> users = jdbcTemplate.query(sql,
                new MapSqlParameterSource("email", email),
                getRowMapper());
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(users.get(0));

    }
}
