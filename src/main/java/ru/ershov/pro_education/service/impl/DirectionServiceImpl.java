package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.DirectionDaoImpl;
import ru.ershov.pro_education.dto.DirectionDto;
import ru.ershov.pro_education.entity.Direction;
import ru.ershov.pro_education.exception.DirectionNotFound;
import ru.ershov.pro_education.mapper.impl.DirectionMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class DirectionServiceImpl extends AbstractCrudService<Direction, DirectionDto, Long> {

    DirectionServiceImpl(DirectionDaoImpl directionDao,
                                   DirectionMapper directionMapper) {
        super(directionDao, directionMapper, DirectionNotFound.class);
    }
}
