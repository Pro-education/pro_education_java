package ru.ershov.pro_education.service;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.InstituteDaoImpl;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.dto.UniversityDto;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.exception.InstituteNotFound;
import ru.ershov.pro_education.mapper.Impl.InstituteMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituteImpl extends AbstractCrud<Institute, InstituteDto, Long> {

    private final UniversityInstituteServiceImpl universityInstituteService;

    protected InstituteImpl(
            InstituteDaoImpl instituteDao,
            InstituteMapper mapper,
            UniversityInstituteServiceImpl universityInstituteService
    ) {
        super(instituteDao, mapper, InstituteNotFound.class);
        this.universityInstituteService = universityInstituteService;
    }

    public List<UniversityDto> findAllUniversities(Long id) {
        return universityInstituteService.findAllUniversities(id)
                .stream()
                .map(universityInstituteService.universityMapper::toDto)
                .collect(Collectors.toList());
    }
}
