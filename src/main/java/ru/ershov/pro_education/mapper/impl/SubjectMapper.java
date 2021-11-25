package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.SubjectDto;
import ru.ershov.pro_education.entity.Subject;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class SubjectMapper extends AbstractMapper<Subject, SubjectDto> {
    SubjectMapper() {
        super(Subject.class, SubjectDto.class);
    }
}
