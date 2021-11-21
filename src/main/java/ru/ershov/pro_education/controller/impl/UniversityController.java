package ru.ershov.pro_education.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.dto.UniversityDto;
import ru.ershov.pro_education.service.impl.UniversityServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController extends AbstractController<UniversityDto, Long> {

    private final UniversityServiceImpl universityService;

    public UniversityController(UniversityServiceImpl service) {
        super(service);
        this.universityService = service;
    }

    @GetMapping("/{id}/institutes")
    public ResponseEntity<List<InstituteDto>> institutes(@PathVariable("id") Long id) {
        return ResponseEntity.ok(universityService.findAllInstitutes(id));
    }

}
