package ru.ershov.pro_education.mapper.Impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.UniversityDto;
import ru.ershov.pro_education.entity.University;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class UniversityMapper extends AbstractMapper<University, UniversityDto> {

    protected UniversityMapper() {
        super(University.class, UniversityDto.class);
    }
}
