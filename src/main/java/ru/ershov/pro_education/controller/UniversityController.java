package ru.ershov.pro_education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.University;
import ru.ershov.pro_education.service.UniversityServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/university")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityServiceImpl universityService;

    @GetMapping
    public ResponseEntity<List<University>> getAllUniversities() {
        return ResponseEntity.ok(universityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable("id") Long id) {
        return ResponseEntity.ok(universityService.findById(id));
    }

    @GetMapping("/insert")
    public ResponseEntity<University> insertU() {
        University university = new University();
        university.setId(12L);
        university.setName("haha");
        return ResponseEntity.ok(universityService.insert(university));
    }

    @GetMapping("/update")
    public ResponseEntity<University> updateU() {
        University university = new University();
        university.setId(12L);
        university.setName("111111111");
        return ResponseEntity.ok(universityService.update(100L, university));
    }

    @GetMapping("/{id}/institutes")
    public ResponseEntity<List<Institute>> institutes(@PathVariable("id") Long id) {
        return ResponseEntity.ok(universityService.findAllInstitutes(id));
    }

}
