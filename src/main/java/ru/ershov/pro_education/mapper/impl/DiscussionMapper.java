package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.DiscussionDto;
import ru.ershov.pro_education.entity.Discussion;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class DiscussionMapper extends AbstractMapper<Discussion, DiscussionDto> {
    protected DiscussionMapper() {
        super(Discussion.class, DiscussionDto.class);
    }
}
