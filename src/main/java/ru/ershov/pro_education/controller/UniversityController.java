package ru.ershov.pro_education.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.dto.UniversityDto;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.service.UniversityImpl;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController extends AbstractController<UniversityDto, Long> {

    private final UniversityImpl universityService;

    protected UniversityController(UniversityImpl crud) {
        super(crud);
        this.universityService = crud;
    }

    @GetMapping("/{id}/institutes")
    public ResponseEntity<List<Institute>> institutes(@PathVariable("id") Long id) {
        return ResponseEntity.ok(universityService.findAllInstitutes(id));
    }

}
