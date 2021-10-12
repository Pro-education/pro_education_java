package ru.ershov.pro_education.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.UniversityInstituteDaoImpl;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.University;
import ru.ershov.pro_education.mapper.Impl.UniversityMapper;
import ru.ershov.pro_education.mapper.Impl.InstituteMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityInstituteServiceImpl {
    private final UniversityInstituteDaoImpl universityInstituteDao;
    public final UniversityMapper universityMapper;
    public final InstituteMapper instituteMapper;

    protected List<Institute> findAllInstitutes(Long id) {
        return universityInstituteDao.findAllInstitutes(id);
    }

    protected List<University> findAllUniversities(Long id) {
        return universityInstituteDao.findAllUniversities(id);
    }
}
