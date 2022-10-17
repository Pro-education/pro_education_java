package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.ReviewDaoImpl;
import ru.ershov.pro_education.dto.DiscussionDto;
import ru.ershov.pro_education.dto.ReviewDto;
import ru.ershov.pro_education.entity.Review;
import ru.ershov.pro_education.exception.not_found.ReviewNotFound;
import ru.ershov.pro_education.mapper.impl.ReviewMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl extends AbstractCrudService<Review, ReviewDto, Long> {

    private final ReviewDaoImpl reviewDao;
    private final DiscussionServiceImpl discussionService;

    protected ReviewServiceImpl(
            ReviewDaoImpl dao,
            ReviewMapper mapper,
            DiscussionServiceImpl discussionService
    ) {
        super(dao, mapper, ReviewNotFound.class);
        this.reviewDao = dao;
        this.discussionService = discussionService;
    }

    public <ID extends Number> List<ReviewDto> getAllReviewByParentId(ID parentId) {
        return reviewDao.getAllReviewByParentId(parentId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DiscussionDto> getAllDiscussionsByReviewId(Long reviewId) {
        return discussionService.getAllByReviewId(reviewId);
    }
}
