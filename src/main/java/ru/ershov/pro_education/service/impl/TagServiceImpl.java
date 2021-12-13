package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.TagDaoImpl;
import ru.ershov.pro_education.dto.TagDto;
import ru.ershov.pro_education.entity.Tag;
import ru.ershov.pro_education.exception.not_found.TagNotFound;
import ru.ershov.pro_education.mapper.impl.TagMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class TagServiceImpl extends AbstractCrudService<Tag, TagDto, Long> {
    protected TagServiceImpl(
            TagDaoImpl dao,
            TagMapper mapper
    ) {
        super(dao, mapper, TagNotFound.class);
    }
}
