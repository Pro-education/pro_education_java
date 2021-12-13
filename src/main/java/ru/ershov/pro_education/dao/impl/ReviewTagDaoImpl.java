package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Review;
import ru.ershov.pro_education.entity.ReviewTag;
import ru.ershov.pro_education.entity.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReviewTagDaoImpl extends AbstractDao<ReviewTag, Long> {
    protected ReviewTagDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, ReviewTag.class);
    }

    public List<Long> findAllTagsByReviewId(Long reviewId) {
        return getAllFromParent(reviewId, Review.class).stream()
                .map(ReviewTag::getTagId)
                .collect(Collectors.toList());
    }

    public List<Long> findAllReviewsByTagId(Long tagId) {
        return getAllFromParent(tagId, Tag.class)
                .stream()
                .map(ReviewTag::getReviewId)
                .collect(Collectors.toList());
    }
}
