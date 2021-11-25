package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.InstituteDepartmentDto;
import ru.ershov.pro_education.entity.InstituteDepartment;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class InstituteDepartmentMapper extends AbstractMapper<InstituteDepartment, InstituteDepartmentDto> {
    InstituteDepartmentMapper() {
        super(InstituteDepartment.class, InstituteDepartmentDto.class);
    }
}
