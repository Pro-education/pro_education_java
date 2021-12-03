package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.ReviewDaoImpl;
import ru.ershov.pro_education.dto.ReviewDto;
import ru.ershov.pro_education.entity.Review;
import ru.ershov.pro_education.exception.not_found.ReviewNotFound;
import ru.ershov.pro_education.mapper.impl.ReviewMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class ReviewServiceImpl extends AbstractCrudService<Review, ReviewDto, Long> {
    protected ReviewServiceImpl(ReviewDaoImpl dao, ReviewMapper mapper) {
        super(dao, mapper, ReviewNotFound.class);
    }
}
