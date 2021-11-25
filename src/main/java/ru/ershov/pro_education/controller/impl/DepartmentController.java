package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.*;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.DepartmentDto;
import ru.ershov.pro_education.dto.DirectionDto;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.service.impl.DepartmentServiceImpl;
import ru.ershov.pro_education.service.impl.InstituteDepartmentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController extends AbstractController<DepartmentDto, Long> {

    private final DepartmentServiceImpl departmentService;
    private final InstituteDepartmentServiceImpl instituteDepartmentService;

    DepartmentController(DepartmentServiceImpl departmentService,
                         InstituteDepartmentServiceImpl instituteDepartmentService) {
        super(departmentService);
        this.departmentService = departmentService;
        this.instituteDepartmentService = instituteDepartmentService;
    }


    @GetMapping("/{departmentId}/institutes")
    public List<InstituteDto> findAllInstitutes(@PathVariable("departmentId") Long departmentId) {
        return instituteDepartmentService.findAllInstitutes(departmentId);
    }

    @GetMapping("/{departmentId}/directions")
    public List<DirectionDto> findAllDirections(@PathVariable("departmentId") Long departmentId){
        return departmentService.findAllDirections(departmentId);
    }

    @PostMapping("/{departmentId}/institutes/{instituteId}")
    public void insertInstitute(@PathVariable("departmentId") Long departmentId,
                                   @PathVariable("instituteId") Long instituteId){
        instituteDepartmentService.connect(instituteId, departmentId);
    }

}
