package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Department;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.InstituteDepartment;

import java.util.List;

@Repository
public class InstituteDepartmentDaoImpl extends AbstractDao<InstituteDepartment, Long> {

    InstituteDepartmentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, InstituteDepartment.class);
    }

    public List<InstituteDepartment> findAllByInstituteId(Long instituteId){
        return getAllFromParent(instituteId, Institute.class);
    }

    public List<InstituteDepartment> findAllByDepartmentId(Long departmentId){
        return getAllFromParent(departmentId, Department.class);
    }
}
