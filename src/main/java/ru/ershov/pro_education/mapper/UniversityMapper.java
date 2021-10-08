package ru.ershov.pro_education.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ershov.pro_education.entity.University;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UniversityMapper implements RowMapper<University> {
    @Override
    public University mapRow(ResultSet resultSet, int i) throws SQLException {
        University university = new University();
        university.setId(resultSet.getLong("id"));
        university.setName(resultSet.getString("name"));
        return university;
    }
}
