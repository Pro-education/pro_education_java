package ru.ershov.pro_education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.dto.UniversityDto;
import ru.ershov.pro_education.service.InstituteImpl;

import java.util.List;

@RestController
@RequestMapping("/institute")
@RequiredArgsConstructor
public class InstituteController {
    private final InstituteImpl instituteService;

    @GetMapping
    public ResponseEntity<List<InstituteDto>> getAllInstitutes() {
        return ResponseEntity.ok(instituteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituteDto> getInstitute(@PathVariable("id") Long id) {
        return ResponseEntity.ok(instituteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<InstituteDto> create(@RequestBody InstituteDto instituteDto) {
        return ResponseEntity.ok(instituteService.insert(instituteDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<InstituteDto> update(@PathVariable("id") Long id, @RequestBody InstituteDto instituteDto) {
        return ResponseEntity.ok(instituteService.update(id, instituteDto));
    }

    @GetMapping("/{id}/universities")
    public ResponseEntity<List<UniversityDto>> universities(@PathVariable("id") Long id) {
        return ResponseEntity.ok(instituteService.findAllUniversities(id));
    }
}
