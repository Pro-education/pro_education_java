package ru.ershov.pro_education.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ershov.pro_education.dto.AbstractDto;
import ru.ershov.pro_education.entity.AbstractEntity;

import java.util.Objects;

public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto> implements Mapper<E, D> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    @Autowired
    private ModelMapper mapper;

    protected AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public D updateDto(D updated, D recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public D updateDto(D updated, E recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public E updateEntity(E updated, D recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public E updateEntity(E updated, E recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }


}
