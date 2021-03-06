package ru.ershov.pro_education.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.ershov.pro_education.service.CrudService;

import java.util.List;

public abstract class AbstractController<D, ID extends Number> implements Controller<D, ID> {

    protected final CrudService<D, ID> service;

    protected AbstractController(CrudService<D, ID> service) {
        this.service = service;
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation("Отдает объект по ID")
    public ResponseEntity<D> findById(@PathVariable("id") ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @ApiOperation("Отдает массив объектов")
    public ResponseEntity<List<D>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @PostMapping
    @ApiOperation("Добавляет объект")
    public ResponseEntity<D> insert(@RequestBody D entity) {
        return ResponseEntity.ok(service.insert(entity));
    }

    @Override
    @PostMapping("/{id}")
    @ApiOperation("Обновляет объект")
    public ResponseEntity<D> update(@PathVariable("id") ID id, @RequestBody D newEntity) {
        return ResponseEntity.ok(service.update(id, newEntity));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаляет объект по ID")
    public ResponseEntity<Boolean> delete(@PathVariable("id") ID id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
