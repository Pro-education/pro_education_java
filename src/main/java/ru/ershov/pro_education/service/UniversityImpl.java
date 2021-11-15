package ru.ershov.pro_education.service;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.UniversityDaoImpl;
import ru.ershov.pro_education.dto.UniversityDto;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.University;
import ru.ershov.pro_education.exception.InstituteNotFound;
import ru.ershov.pro_education.mapper.Impl.UniversityMapper;

import java.util.List;

@Service
public class UniversityImpl extends AbstractCrud<University, UniversityDto, Long> {

    private final UniversityInstituteServiceImpl universityInstituteService;

    protected UniversityImpl(
            UniversityDaoImpl dao,
            UniversityMapper mapper,
            UniversityInstituteServiceImpl universityInstituteService
    ) {
        super(dao, mapper, InstituteNotFound.class);
        this.universityInstituteService = universityInstituteService;
    }

    /**
     * Find all {@link Institute}s list by {@link University}.
     *
     * @param id the {@link University} id
     * @return the {@link List} of {@link Institute}s
     */
    public List<Institute> findAllInstitutes(Long id) {
        return universityInstituteService.findAllInstitutes(id);
    }
}
