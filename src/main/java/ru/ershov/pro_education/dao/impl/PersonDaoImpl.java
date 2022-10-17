package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonDaoImpl extends AbstractDao<Person, Long> {

    private final String sql = super.getBaseSqlQuery() + " where email = :email";


    protected PersonDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Person.class);
    }

    public Optional<Person> findByEmail(String email) {
        List<Person> users = super.jdbcTemplate.query(sql,
                new MapSqlParameterSource("email", email),
                getRowMapper());
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(users.get(0));
    }

    public <S extends Person> S createPerson(S person) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("role_name", "ROLE_USER");
        mapSqlParameterSource.addValue("username", person.getUsername());
        mapSqlParameterSource.addValue("name", person.getName());
        mapSqlParameterSource.addValue("surname", person.getSurname());
        mapSqlParameterSource.addValue("vk_link", person.getVkLink());
        mapSqlParameterSource.addValue("email", person.getEmail());
        mapSqlParameterSource.addValue("password", person.getPassword());
        jdbcTemplate.getJdbcTemplate().update(
                "call public.addPerson (?, ?, ?, ?, ?, ?, ?)",
                "ROLE_USER", person.getUsername(), person.getName(), person.getSurname(),
                person.getVkLink(), person.getEmail(), person.getPassword());
        return person;
    }
}
