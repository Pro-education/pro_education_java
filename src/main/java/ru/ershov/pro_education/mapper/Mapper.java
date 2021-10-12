package ru.ershov.pro_education.mapper;

import ru.ershov.pro_education.dto.AbstractDto;
import ru.ershov.pro_education.entity.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    D updateDto(D updated, D recent);

    D updateDto(D updated, E recent);

    E updateEntity(E updated, D recent);

    E updateEntity(E updated, E recent);

    E toEntity(D dto);

    D toDto(E entity);
}
