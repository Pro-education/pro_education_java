package ru.ershov.pro_education.service;

import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.dto.AbstractDto;
import ru.ershov.pro_education.entity.AbstractEntity;
import ru.ershov.pro_education.mapper.AbstractMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractCrud<E extends AbstractEntity, D extends AbstractDto, ID extends Number> implements Crud<D, ID> {

    private final AbstractDao<E, ID> dao;
    protected final AbstractMapper<E, D> mapper;
    private final Class<? extends RuntimeException> exception;

    protected AbstractCrud(AbstractDao<E, ID> dao, AbstractMapper<E, D> mapper, Class<? extends RuntimeException> exception) {
        this.dao = dao;
        this.mapper = mapper;
        this.exception = exception;
    }

    private Supplier<RuntimeException> throwException(ID id) {
        return () -> {
            try {
                return exception.getConstructor(Number.class).newInstance(id);
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                return new RuntimeException("no entity bu id: " + id);
            }
        };
    }

    @Override
    public D findById(ID id) {
        return mapper.toDto(dao.findById(id).orElseThrow(throwException(id)));
    }

    @Override
    public List<D> findAll() {
        return dao.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public <S extends D> S insert(S entity) {
        return (S) mapper.toDto(dao.insert(mapper.toEntity(entity)));
    }

    @Override
    public <S extends D> S update(ID id, S newEntity) {
        return (S) mapper.toDto(dao.update(id, mapper.toEntity(newEntity)));
    }

    @Override
    public boolean existById(ID id) {
        return dao.existById(id);
    }

    @Override
    public boolean delete(ID id) {
        return dao.delete(id);
    }
}
