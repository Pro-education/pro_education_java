package ru.ershov.pro_education.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID>  {

    Optional<T> findById(ID id);

    List<T> findAll();

    <S extends T> S save(S entity);
}
