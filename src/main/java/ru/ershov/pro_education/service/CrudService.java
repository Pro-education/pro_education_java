package ru.ershov.pro_education.service;

import ru.ershov.pro_education.dto.PersonDto;

import java.util.List;

public interface CrudService<T, ID> {

    T findById(ID id);

    List<T> findAll();

    List<T> findAllByStatus(Status status);

    <S extends T> S insert(S entity);

    <S extends T> S update(ID id, S newEntity);

    boolean existById(ID id);

    boolean delete(ID id);

    void passedUpdateStatus(ID id, Long approverId);

    void notPassedUpdateStatus(ID id, Long approverId);

    PersonDto getApprover(ID id);
}
