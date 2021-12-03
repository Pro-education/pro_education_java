package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.InstituteDepartmentDaoImpl;
import ru.ershov.pro_education.dto.DepartmentDto;
import ru.ershov.pro_education.dto.InstituteDepartmentDto;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.entity.InstituteDepartment;
import ru.ershov.pro_education.mapper.impl.InstituteDepartmentMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituteDepartmentServiceImpl extends AbstractCrudService<InstituteDepartment, InstituteDepartmentDto, Long> {

    private final InstituteDepartmentDaoImpl instituteDepartmentDao;
    private final DepartmentServiceImpl departmentService;
    private final InstituteServiceImpl instituteService;

    InstituteDepartmentServiceImpl(
            InstituteDepartmentDaoImpl instituteDepartmentDao,
            InstituteDepartmentMapper instituteDepartmentMapper,
            DepartmentServiceImpl departmentService,
            InstituteServiceImpl instituteService
    ) {
        super(instituteDepartmentDao, instituteDepartmentMapper, RuntimeException.class);
        this.instituteDepartmentDao = instituteDepartmentDao;
        this.departmentService = departmentService;
        this.instituteService = instituteService;
    }

    public void connect(Long instituteId, Long departmentId) {
        InstituteDepartment instituteDepartment = new InstituteDepartment(instituteId, departmentId);
        instituteDepartmentDao.insert(instituteDepartment);
    }

    public List<DepartmentDto> findAllByInstituteId(Long instituteId){
        return instituteDepartmentDao.findAllDepartmentsByInstituteId(instituteId)
                .stream()
                .map(departmentService::findById)
                .collect(Collectors.toList());
    }

    public List<InstituteDto> findAllByDepartmentId(Long departmentId){
        return instituteDepartmentDao.findAllInstitutesByDepartmentId(departmentId)
                .stream()
                .map(instituteService::findById)
                .collect(Collectors.toList());
    }
}
