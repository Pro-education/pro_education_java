package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.DepartmentDaoImpl;
import ru.ershov.pro_education.dto.DepartmentDto;
import ru.ershov.pro_education.dto.DirectionDto;
import ru.ershov.pro_education.entity.Department;
import ru.ershov.pro_education.mapper.impl.DepartmentMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;

@Service
public class DepartmentServiceImpl extends AbstractCrudService<Department, DepartmentDto, Long> {

    private final DirectionServiceImpl directionService;

    public DepartmentServiceImpl(DepartmentDaoImpl departmentDao,
                                 DepartmentMapper departmentMapper,
                                 DirectionServiceImpl directionService) {
        super(departmentDao, departmentMapper, RuntimeException.class);
        this.directionService = directionService;
    }

    public List<DirectionDto> findAllDirections(Long departmentId){
        return directionService.findAllByDepartmentId(departmentId);
    }
}
