package ru.ershov.pro_education.service;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class InstituteServiceImpl implements CrudService<InstituteDto, Long>{

    private final InstituteDaoImpl instituteDao;
    private final UniversityInstituteServiceImpl universityInstituteService;
    private final InstituteMapper mapper;

    @Override
    public InstituteDto findById(Long id) {
        return mapper.toDto(instituteDao.findById(id).orElseThrow(() -> new InstituteNotFound(id)));
    }

    @Override
    public List<InstituteDto> findAll() {
        return instituteDao.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InstituteDto insert(InstituteDto entity) {
        return mapper.toDto(instituteDao.insert(mapper.toEntity(entity)));
    }

    @Override
    public InstituteDto update(Long id, InstituteDto newEntity) {
        Institute byId = instituteDao.findById(id).orElseThrow(() -> new InstituteNotFound(id));
        return mapper.toDto(instituteDao.update(id, mapper.updateEntity(byId, newEntity)));
    }

    @Override
    public boolean existById(Long id) {
        return instituteDao.existById(id);
    }

    public List<UniversityDto> findAllUniversities(Long id) {
        System.out.println(universityInstituteService.findAllUniversities(id));
        return universityInstituteService.findAllUniversities(id)
                .stream()
                .map(universityInstituteService.universityMapper::toDto)
                .collect(Collectors.toList());
    }
}
