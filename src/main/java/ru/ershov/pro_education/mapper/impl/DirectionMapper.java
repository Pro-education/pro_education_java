package ru.ershov.pro_education.mapper.impl;


import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.DirectionDto;
import ru.ershov.pro_education.entity.Direction;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class DirectionMapper extends AbstractMapper<Direction, DirectionDto> {

    DirectionMapper() {
        super(Direction.class, DirectionDto.class);
    }
}
