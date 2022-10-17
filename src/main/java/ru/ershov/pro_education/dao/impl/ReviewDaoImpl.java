package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Review;

import java.util.List;

@Repository
public class ReviewDaoImpl extends AbstractDao<Review, Long> {

    protected ReviewDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Review.class);
    }

    public <ID extends Number> List<Review> getAllReviewByParentId(ID parentId) {
        return jdbcTemplate.query(getBaseSqlQuery() + "where table_id = " + parentId, getRowMapper());
    }

    //department
    //direction
    //institute
    //subject
    //university
    //team
}
