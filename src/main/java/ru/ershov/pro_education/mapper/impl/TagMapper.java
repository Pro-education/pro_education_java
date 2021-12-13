package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.TagDto;
import ru.ershov.pro_education.entity.Tag;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class TagMapper extends AbstractMapper<Tag, TagDto> {
    protected TagMapper() {
        super(Tag.class, TagDto.class);
    }
}
