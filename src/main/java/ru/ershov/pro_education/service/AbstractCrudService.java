package ru.ershov.pro_education.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.dto.AbstractDto;
import ru.ershov.pro_education.dto.PersonDto;
import ru.ershov.pro_education.entity.AbstractEntity;
import ru.ershov.pro_education.mapper.AbstractMapper;
import ru.ershov.pro_education.service.impl.PersonServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractCrudService<E extends AbstractEntity, D extends AbstractDto, ID extends Number>
        implements CrudService<D, ID> {

    @Autowired
    private PersonServiceImpl personService;

    protected final AbstractDao<E, ID> dao;
    protected final AbstractMapper<E, D> mapper;
    private final Class<? extends RuntimeException> exception;

    protected AbstractCrudService(AbstractDao<E, ID> dao, AbstractMapper<E, D> mapper, Class<? extends RuntimeException> exception) {
        this.dao = dao;
        this.mapper = mapper;
        this.exception = exception;
    }

    protected Supplier<RuntimeException> throwException(ID id) {
        return () -> {
            try {
                return exception.getConstructor(Number.class).newInstance(id);
            } catch (InstantiationException | InvocationTargetException
                    | IllegalAccessException | NoSuchMethodException e) {
                return new RuntimeException("no entity by id: " + id);
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

    public List<D> findAllByStatus(Status status) {
        return dao.findAllByStatus(status)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public <S extends D> S insert(S entity) {
        entity.setCheckStatus(Status.IN_CHECK);
        return (S) mapper.toDto(dao.insert(mapper.toEntity(entity)));
    }

    @Override
    public <S extends D> S update(ID id, S newEntity) {
        newEntity.setCheckStatus(Status.IN_CHECK);
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

    @Override
    public void passedUpdateStatus(ID id, Long approverId) {
        E entity = dao.findById(id).orElseThrow(throwException(id));
        entity.setApproverId(approverId);
        entity.setCheckStatus(getNextStatus(entity.getCheckStatus()));
        dao.update(id, entity);
    }

    @Override
    public void notPassedUpdateStatus(ID id, Long approverId) {
        E entity = dao.findById(id).orElseThrow(throwException(id));
        entity.setApproverId(approverId);
        entity.setCheckStatus(Status.NOT_PASSED.name());
        dao.update(id, entity);
    }

    private String getNextStatus(String current) {
        return Status.next(Status.valueOf(current)).name();
    }

    public PersonDto getApprover(ID id) {
        E e = dao.findById(id).orElseThrow(throwException(id));
        return personService.findById(e.getApproverId());
    }

}
