package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.University;
import ru.ershov.pro_education.entity.UniversityInstitute;

import java.util.List;

@Repository
public class UniversityInstituteDaoImpl extends AbstractDao<UniversityInstitute, Long> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UniversityDaoImpl universityDao;
    private final InstituteDaoImpl instituteDao;

    protected UniversityInstituteDaoImpl(
            NamedParameterJdbcTemplate jdbcTemplate,
            UniversityDaoImpl universityDao,
            InstituteDaoImpl instituteDao
    ) {
        super(jdbcTemplate, UniversityInstitute.class);
        this.jdbcTemplate = jdbcTemplate;
        this.universityDao = universityDao;
        this.instituteDao = instituteDao;
    }

    public <ID extends Number> List<Institute> findAllInstitutes(ID id) {
        MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource("id", id);
        String instituteName = instituteDao.getTableName();
        String sql = "SELECT " + instituteDao.findColumnAndIdDbName() + " FROM " + universityDao.getTableName()
                + " AS u JOIN " + getTableName() + " AS j ON u.id = j.university_id"
                + " JOIN " + instituteName + " ON " + instituteName + ".id = j.institute_id WHERE u.id = :id";
        return jdbcTemplate.query(sql, mapSqlParameterSource, instituteDao.getRowMapper());
    }

    public <ID extends Number> List<University> findAllUniversities(ID id) {
        MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource("id", id);
        String universityName = universityDao.getTableName();
        String sql = "SELECT " + universityDao.findColumnAndIdDbName() + " FROM " + instituteDao.getTableName()
                + " AS i JOIN " + getTableName() + " AS j ON i.id = j.institute_id"
                + " JOIN " + universityName + " ON " + universityName + ".id = j.university_id WHERE i.id = :id";
        return jdbcTemplate.query(sql, mapSqlParameterSource, universityDao.getRowMapper());
    }
}
