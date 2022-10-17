package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.DiscussionDaoImpl;
import ru.ershov.pro_education.dto.DiscussionDto;
import ru.ershov.pro_education.entity.Discussion;
import ru.ershov.pro_education.exception.not_found.DiscussionNotFound;
import ru.ershov.pro_education.mapper.impl.DiscussionMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscussionServiceImpl extends AbstractCrudService<Discussion, DiscussionDto, Long> {

    private final DiscussionMapper mapper;
    private final DiscussionDaoImpl dao;

    protected DiscussionServiceImpl(DiscussionDaoImpl dao, DiscussionMapper mapper) {
        super(dao, mapper, DiscussionNotFound.class);
        this.dao = dao;
        this.mapper = mapper;
    }

    public List<DiscussionDto> getAllByReviewId(Long reviewId) {
        return dao.getAllReviewByParentId(reviewId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
