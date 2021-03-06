package ru.ershov.pro_education.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID extends Number> {

    Optional<T> findById(ID id);

    boolean existById(ID id);

    List<T> findAll();

    <S extends T> S insert(S entity);

    <S extends T> S update(ID id, S newEntity);

    boolean delete(ID id);
}
