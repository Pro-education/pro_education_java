package ru.ershov.pro_education.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Controller<D, ID extends Number> {

    ResponseEntity<D> findById(ID id);

    ResponseEntity<List<D>> findAll();

    ResponseEntity<D> insert(D entity);

    ResponseEntity<D> update(ID id, D newEntity);

}
