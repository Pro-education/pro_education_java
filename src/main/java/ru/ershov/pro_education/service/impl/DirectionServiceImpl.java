package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.DirectionDaoImpl;
import ru.ershov.pro_education.dto.DirectionDto;
import ru.ershov.pro_education.entity.Direction;
import ru.ershov.pro_education.exception.DirectionNotFound;
import ru.ershov.pro_education.mapper.impl.DirectionMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectionServiceImpl extends AbstractCrudService<Direction, DirectionDto, Long> {

    private final DirectionDaoImpl directionDao;

    DirectionServiceImpl(DirectionDaoImpl directionDao,
                                   DirectionMapper directionMapper) {
        super(directionDao, directionMapper, DirectionNotFound.class);
        this.directionDao = directionDao;
    }

    public List<DirectionDto> findAllByDepartmentId(Long departmentId){
        return directionDao.findAllByDepartmentId(departmentId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
