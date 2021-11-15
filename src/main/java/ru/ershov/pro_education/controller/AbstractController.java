package ru.ershov.pro_education.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ershov.pro_education.service.Crud;

import java.util.List;

public abstract class AbstractController<D, ID extends Number> implements Controller<D, ID> {

    private final Crud<D, ID> crud;

    protected AbstractController(Crud<D, ID> crud) {
        this.crud = crud;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable("id") ID id) {
        return ResponseEntity.ok(crud.findById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<D>> findAll() {
        return ResponseEntity.ok(crud.findAll());
    }

    @Override
    @PostMapping
    public ResponseEntity<D> insert(@RequestBody D entity) {
        return ResponseEntity.ok(crud.insert(entity));
    }

    @Override
    @PostMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable("id") ID id, @RequestBody D newEntity) {
        return ResponseEntity.ok(crud.update(id, newEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") ID id) {
        return ResponseEntity.ok(crud.delete(id));
    }

}
