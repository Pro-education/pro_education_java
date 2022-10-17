package ru.ershov.pro_education.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.ershov.pro_education.dto.AbstractDto;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.service.CrudService;
import ru.ershov.pro_education.service.Status;

import java.util.List;

public abstract class AbstractController<D extends AbstractDto, ID extends Number> implements Controller<D, ID> {

    protected final CrudService<D, ID> service;

    protected AbstractController(CrudService<D, ID> service) {
        this.service = service;
    }

    @Override
    @GetMapping("/{id}")
    @Operation(description = "Отдает объект по ID")
    public ResponseEntity<D> findById(@PathVariable("id") ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @GetMapping
    @Operation(description = "Отдает массив объектов")
    public ResponseEntity<List<D>> findAll() {
        return ResponseEntity.ok(service.findAllByStatus(Status.OK));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    @Operation(description = "Отдает массив объектов")
    public ResponseEntity<List<D>> findAllByStatus(Status status, @AuthenticationPrincipal Person person) {
        return ResponseEntity.ok(service.findAllByStatus(status));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Operation(description = "Добавляет объект")
    public ResponseEntity<D> insert(@RequestBody D entity, @AuthenticationPrincipal Person person) {
        return ResponseEntity.ok(service.insert(entity));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    @Operation(description = "Обновляет объект")
    public ResponseEntity<D> update(
            @PathVariable("id") ID id,
            @RequestBody D newEntity,
            @AuthenticationPrincipal Person person
    ) {
        newEntity.setApproverId(person.getId());
        return ResponseEntity.ok(service.update(id, newEntity));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    @Operation(description = "Обновляет объект")
    public void nextStatus(
            @PathVariable("id") ID id,
            @AuthenticationPrincipal Person person
    ) {
        service.passedUpdateStatus(id, person.getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Удаляет объект по ID")
    public ResponseEntity<Boolean> delete(@PathVariable("id") ID id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
