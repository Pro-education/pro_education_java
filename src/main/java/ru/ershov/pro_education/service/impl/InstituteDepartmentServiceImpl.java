package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.InstituteDepartmentDaoImpl;
import ru.ershov.pro_education.dto.InstituteDepartmentDto;
import ru.ershov.pro_education.entity.InstituteDepartment;
import ru.ershov.pro_education.mapper.impl.InstituteDepartmentMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituteDepartmentServiceImpl extends AbstractCrudService<InstituteDepartment, InstituteDepartmentDto, Long> {

    private final InstituteDepartmentDaoImpl instituteDepartmentDao;
//    private final DepartmentServiceImpl departmentService;
//    private final InstituteServiceImpl instituteService;

    InstituteDepartmentServiceImpl(InstituteDepartmentDaoImpl instituteDepartmentDao,
                                   InstituteDepartmentMapper instituteDepartmentMapper) {
        super(instituteDepartmentDao, instituteDepartmentMapper, RuntimeException.class);
        this.instituteDepartmentDao = instituteDepartmentDao;
//        this.departmentService = departmentService;
//        this.instituteService = instituteService;
    }

    public void connect(Long instituteId, Long departmentId){
        InstituteDepartment instituteDepartment = new InstituteDepartment(instituteId, departmentId);
        instituteDepartmentDao.insert(instituteDepartment);
    }

    List<InstituteDepartmentDto> findAllByDepartmentIdDto(Long departmentId){
        return instituteDepartmentDao.findAllByDepartmentId(departmentId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    List<InstituteDepartment> findAllByDepartmentId(Long departmentId){
        return instituteDepartmentDao.findAllByDepartmentId(departmentId);
    }

    List<InstituteDepartmentDto> findAllByInstituteIdDto(Long instituteId){
        return instituteDepartmentDao.findAllByDepartmentId(instituteId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    List<InstituteDepartment> findAllByInstituteId(Long instituteId){
        return instituteDepartmentDao.findAllByInstituteId(instituteId);
    }
}
