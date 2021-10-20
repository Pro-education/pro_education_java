package ru.ershov.pro_education.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.UniversityDaoImpl;
import ru.ershov.pro_education.entity.Institute;
import ru.ershov.pro_education.entity.University;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UniversityImpl implements Crud<University, Long> {

    private final UniversityDaoImpl dao;
    private final UniversityInstituteServiceImpl universityInstituteService;

    @Override
    public University findById(Long id) {
        return dao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<University> findAll() {
        return dao.findAll();
    }

    @Override
    public University insert(University entity) {
        return dao.insert(entity);
    }

    @Override
    public University update(Long id, University newEntity) {
        return dao.update(id, newEntity);
    }

    @Override
    public boolean existById(Long id) {
        return dao.existById(id);
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
