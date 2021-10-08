package ru.ershov.pro_education.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.University;

import java.sql.ResultSet;

@Repository
@RequiredArgsConstructor
class UniversityDaoImpl extends AbstractDao<University, Long> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    protected RowMapper<University> getRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            University university = new University();
            university.setId(rs.getLong("id"));
            university.setName(rs.getString("name"));
            return university;
        };
    }

    @Override
    protected NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    protected Class<University> getTableClass() {
        return University.class;
    }

}
