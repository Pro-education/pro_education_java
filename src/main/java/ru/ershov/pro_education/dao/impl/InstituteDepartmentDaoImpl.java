package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Department;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.InstituteDepartment;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InstituteDepartmentDaoImpl extends AbstractDao<InstituteDepartment, Long> {

    InstituteDepartmentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, InstituteDepartment.class);
    }

    public List<Long> findAllDepartmentsByInstituteId(Long instituteId){
        return getAllFromParent(instituteId, Institute.class).stream()
                .map(InstituteDepartment::getDepartmentId)
                .collect(Collectors.toList());
    }

    public List<Long> findAllInstitutesByDepartmentId(Long departmentId){
        return getAllFromParent(departmentId, Department.class).stream()
                .map(InstituteDepartment::getInstituteId)
                .collect(Collectors.toList());
    }
}
