package ru.ershov.pro_education.service;

import java.util.List;

public interface CrudService<T, ID> {

    T findById(ID id);

    List<T> findAll();

    <S extends T> S insert(S entity);

    <S extends T> S update(ID id, S newEntity);

    boolean existById(ID id);
}
