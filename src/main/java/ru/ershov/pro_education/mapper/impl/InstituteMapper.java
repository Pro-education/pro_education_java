package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class InstituteMapper extends AbstractMapper<Institute, InstituteDto> {

    protected InstituteMapper() {
        super(Institute.class, InstituteDto.class);
    }
}
