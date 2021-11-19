package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.UniversityDaoImpl;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.dto.UniversityDto;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.University;
import ru.ershov.pro_education.exception.InstituteNotFound;
import ru.ershov.pro_education.mapper.impl.UniversityMapper;
import ru.ershov.pro_education.service.AbstractCrud;

import java.util.List;

@Service
public class UniversityImpl extends AbstractCrud<University, UniversityDto, Long> {

    private final InstituteImpl instituteService;

    protected UniversityImpl(
            UniversityDaoImpl dao,
            UniversityMapper mapper,
            InstituteImpl instituteService) {
        super(dao, mapper, InstituteNotFound.class);
        this.instituteService = instituteService;
    }

    /**
     * Find all {@link Institute}s list by {@link University}.
     *
     * @param id the {@link University} id
     * @return the {@link List} of {@link Institute}s
     */
    public List<InstituteDto> findAllInstitutes(Long id) {
        return instituteService.findAllByUniversityId(id);
    }
}
