package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.*;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.controller.AbstractWithReviewController;
import ru.ershov.pro_education.dto.DepartmentDto;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.service.impl.InstituteDepartmentServiceImpl;
import ru.ershov.pro_education.service.impl.InstituteServiceImpl;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/institute")
public class InstituteController extends AbstractWithReviewController<InstituteDto, Long> {

    private final InstituteDepartmentServiceImpl instituteDepartmentService;

    public InstituteController(InstituteServiceImpl instituteService,
                               InstituteDepartmentServiceImpl instituteDepartmentService) {
        super(instituteService);
        this.instituteDepartmentService = instituteDepartmentService;
    }

    @GetMapping("/{instituteId}/departments")
    public List<DepartmentDto> findAllDepartments(@PathVariable("instituteId") Long instituteId){
        return instituteDepartmentService.findAllByInstituteId(instituteId);
    }

    @PostMapping("/{instituteId}/departments/{departmentId}")
    public void insertDepartment(@PathVariable("instituteId") Long instituteId,
                                @PathVariable("departmentId") Long departmentId){
        instituteDepartmentService.connect(instituteId, departmentId);
    }
}
