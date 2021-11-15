package ru.ershov.pro_education.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.dto.UniversityDto;
import ru.ershov.pro_education.service.InstituteImpl;

import java.util.List;

@Controller
@RequestMapping("/institute")
public class InstituteController extends AbstractController<InstituteDto, Long>{

    private final InstituteImpl instituteService;

    protected InstituteController(InstituteImpl crud) {
        super(crud);
        this.instituteService = crud;
    }

    @GetMapping("/{id}/universities")
    public ResponseEntity<List<UniversityDto>> universities(@PathVariable("id") Long id) {
        return ResponseEntity.ok(instituteService.findAllUniversities(id));
    }
}
