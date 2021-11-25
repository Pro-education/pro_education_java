package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.DepartmentDaoImpl;
import ru.ershov.pro_education.dao.impl.InstituteDaoImpl;
import ru.ershov.pro_education.dao.impl.InstituteDepartmentDaoImpl;
import ru.ershov.pro_education.dto.DepartmentDto;
import ru.ershov.pro_education.dto.InstituteDepartmentDto;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.entity.InstituteDepartment;
import ru.ershov.pro_education.mapper.impl.DepartmentMapper;
import ru.ershov.pro_education.mapper.impl.InstituteDepartmentMapper;
import ru.ershov.pro_education.mapper.impl.InstituteMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituteDepartmentServiceImpl extends AbstractCrudService<InstituteDepartment, InstituteDepartmentDto, Long> {

    private final InstituteDepartmentDaoImpl instituteDepartmentDao;
    private final DepartmentDaoImpl departmentDao;
    private final InstituteDaoImpl instituteDao;
    private final DepartmentMapper departmentMapper;
    private final InstituteMapper instituteMapper;

    InstituteDepartmentServiceImpl(InstituteDepartmentDaoImpl instituteDepartmentDao,
                                   InstituteDepartmentMapper instituteDepartmentMapper,
                                   DepartmentDaoImpl departmentDao,
                                   InstituteDaoImpl instituteDao,
                                   DepartmentMapper departmentMapper,
                                   InstituteMapper instituteMapper) {
        super(instituteDepartmentDao, instituteDepartmentMapper, RuntimeException.class);
        this.instituteDepartmentDao = instituteDepartmentDao;
        this.departmentDao = departmentDao;
        this.instituteDao = instituteDao;
        this.departmentMapper = departmentMapper;
        this.instituteMapper = instituteMapper;
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

    public List<DepartmentDto> findAllDepartments(Long instituteId){
        return findAllByInstituteId(instituteId)
                .stream()
                .map(instituteDepartment -> departmentMapper.toDto(departmentDao.findById(instituteDepartment
                        .getDepartmentId()).orElseThrow(() -> new RuntimeException("Not Found"))))
                .collect(Collectors.toList());
    }

    public List<InstituteDto> findAllInstitutes(Long departmentId){
        return findAllByDepartmentId(departmentId)
                .stream()
                .map(instituteDepartment -> instituteMapper.toDto(instituteDao.findById(instituteDepartment
                        .getInstituteId()).orElseThrow(() -> new RuntimeException("Not Found"))))
                .collect(Collectors.toList());
    }

    List<InstituteDepartment> findAllByDepartmentId(Long departmentId){
        return instituteDepartmentDao.findAllByDepartmentId(departmentId);
    }


    List<InstituteDepartment> findAllByInstituteId(Long instituteId){
        return instituteDepartmentDao.findAllByInstituteId(instituteId);
    }

    List<InstituteDepartmentDto> findAllByInstituteIdDto(Long instituteId){
        return instituteDepartmentDao.findAllByDepartmentId(instituteId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
