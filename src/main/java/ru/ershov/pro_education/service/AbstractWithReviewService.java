package ru.ershov.pro_education.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.dto.AbstractDto;
import ru.ershov.pro_education.dto.ReviewDto;
import ru.ershov.pro_education.entity.AbstractEntity;
import ru.ershov.pro_education.mapper.AbstractMapper;
import ru.ershov.pro_education.service.impl.ReviewServiceImpl;

import java.util.List;

public abstract class AbstractWithReviewService<E extends AbstractEntity, D extends AbstractDto, ID extends Number>
        extends AbstractCrudService<E, D, ID> {

    @Autowired
    private ReviewServiceImpl reviewService;

    protected AbstractWithReviewService(
            AbstractDao<E, ID> dao,
            AbstractMapper<E, D> mapper,
            Class<? extends RuntimeException> exception
    ) {
        super(dao, mapper, exception);
    }

    public List<ReviewDto> findAllReviews(ID parentId) {
        return reviewService.getAllReviewByParentId(parentId);
    }

    public ReviewDto createReview(ID parentId, ReviewDto reviewDto) {
        reviewDto.setTableId((Long) parentId);
        return reviewService.insert(reviewDto);
    }

}
