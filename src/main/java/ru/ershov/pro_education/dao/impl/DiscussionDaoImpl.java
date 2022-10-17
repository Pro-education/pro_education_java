package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Discussion;
import ru.ershov.pro_education.entity.Review;

import java.util.List;

@Repository
public class DiscussionDaoImpl extends AbstractDao<Discussion, Long> {
    protected DiscussionDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Discussion.class);
    }

    public List<Discussion> getAllReviewByParentId(Long parentId) {
        return getAllFromParent(parentId, Review.class);
    }
}
