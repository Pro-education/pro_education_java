package ru.ershov.pro_education.controller;

import org.springframework.http.ResponseEntity;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.service.Status;

import java.util.List;

public interface Controller<D, ID extends Number> {

    ResponseEntity<D> findById(ID id);

    ResponseEntity<List<D>> findAll();

    ResponseEntity<List<D>> findAllByStatus(Status status, Person person);

    ResponseEntity<D> insert(D entity, Person person);

    ResponseEntity<D> update(ID id, D newEntity, Person person);

    void nextStatus(ID id, Person person);
}
