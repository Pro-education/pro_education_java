package ru.ershov.pro_education.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.Dao;
import ru.ershov.pro_education.entity.University;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UniversityCrudServiceImpl implements CrudService<University> {

    private final Dao<University, Long> dao;

    @Override
    public University findById(Long id) {
        return dao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<University> findAll() {
        return dao.findAll();
    }

    @Override
    public <S extends University> S save(S entity) {
        return dao.save(entity);
    }
}
