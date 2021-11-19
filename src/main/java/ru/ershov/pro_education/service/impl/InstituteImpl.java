package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.InstituteDaoImpl;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.University;
import ru.ershov.pro_education.exception.InstituteNotFound;
import ru.ershov.pro_education.mapper.impl.InstituteMapper;
import ru.ershov.pro_education.service.AbstractCrud;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituteImpl extends AbstractCrud<Institute, InstituteDto, Long> {

    protected InstituteImpl(
            InstituteDaoImpl instituteDao,
            InstituteMapper mapper
    ) {
        super(instituteDao, mapper, InstituteNotFound.class);
    }

    List<InstituteDto> findAllByUniversityId(Long universityId) {
        return dao.getAllFromParent(universityId, University.class)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
