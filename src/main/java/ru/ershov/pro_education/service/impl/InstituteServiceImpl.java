package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.InstituteDaoImpl;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.exception.InstituteNotFound;
import ru.ershov.pro_education.mapper.impl.InstituteMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituteServiceImpl extends AbstractCrudService<Institute, InstituteDto, Long> {

    private final InstituteDaoImpl instituteDao;
    private final InstituteDepartmentServiceImpl instituteDepartmentService;

    protected InstituteServiceImpl(
            InstituteDaoImpl instituteDao,
            InstituteMapper mapper,
            InstituteDepartmentServiceImpl instituteDepartmentService
    ) {
        super(instituteDao, mapper, InstituteNotFound.class);
        this.instituteDao = instituteDao;
        this.instituteDepartmentService = instituteDepartmentService;
    }

    public List<InstituteDto> findAllByUniversityId(Long universityId) {
        return instituteDao.findAllByUniversityId(universityId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<InstituteDto> findAllByDepartmentId(Long departmentId){
        return instituteDepartmentService.findAllByDepartmentId(departmentId)
                .stream()
                .map(instituteDepartment -> findById(instituteDepartment.getInstituteId()))
                .collect(Collectors.toList());
    }
}
