package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.DiscussionDaoImpl;
import ru.ershov.pro_education.dto.DiscussionDto;
import ru.ershov.pro_education.entity.Discussion;
import ru.ershov.pro_education.exception.not_found.DiscussionNotFound;
import ru.ershov.pro_education.mapper.impl.DiscussionMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class DiscussionServiceImpl extends AbstractCrudService<Discussion, DiscussionDto, Long> {
    protected DiscussionServiceImpl(DiscussionDaoImpl dao, DiscussionMapper mapper) {
        super(dao, mapper, DiscussionNotFound.class);
    }
}
