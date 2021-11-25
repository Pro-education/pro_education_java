package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.DepartmentDto;
import ru.ershov.pro_education.entity.Department;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class DepartmentMapper extends AbstractMapper<Department, DepartmentDto> {
    DepartmentMapper() {
        super(Department.class, DepartmentDto.class);
    }
}
