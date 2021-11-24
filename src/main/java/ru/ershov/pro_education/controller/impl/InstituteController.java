package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.*;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.DepartmentDto;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.service.impl.DepartmentServiceImpl;
import ru.ershov.pro_education.service.impl.InstituteDepartmentServiceImpl;
import ru.ershov.pro_education.service.impl.InstituteServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/institute")
public class InstituteController extends AbstractController<InstituteDto, Long> {

    private final DepartmentServiceImpl departmentService;
    private final InstituteDepartmentServiceImpl instituteDepartmentService;

    public InstituteController(InstituteServiceImpl instituteService,
                               DepartmentServiceImpl departmentService,
                               InstituteDepartmentServiceImpl instituteDepartmentService) {
        super(instituteService);
        this.departmentService = departmentService;
        this.instituteDepartmentService = instituteDepartmentService;
    }

    @GetMapping("/{instituteId}/departments")
    public List<DepartmentDto> findAllDepartments(@PathVariable("instituteId") Long instituteId){
        return departmentService.findAllByInstituteId(instituteId);
    }

    @PostMapping("/{instituteId}/departments/{departmentId}")
    public void insertDepartment(@PathVariable("instituteId") Long instituteId,
                                @PathVariable("departmentId") Long departmentId){
        instituteDepartmentService.connect(instituteId, departmentId);
    }
}
