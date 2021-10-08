package ru.ershov.pro_education.service;

import java.util.List;

public interface CrudService<T> {

    T findById(Long id);

    List<T> findAll();

    <S extends T> S save(S entity);
}
