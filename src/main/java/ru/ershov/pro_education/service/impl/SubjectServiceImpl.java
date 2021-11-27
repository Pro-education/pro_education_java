package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.SubjectDaoImpl;
import ru.ershov.pro_education.dto.SubjectDto;
import ru.ershov.pro_education.entity.Subject;
import ru.ershov.pro_education.exception.not_found.SubjectNotFound;
import ru.ershov.pro_education.mapper.impl.SubjectMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class SubjectServiceImpl extends AbstractCrudService<Subject, SubjectDto, Long> {
    SubjectServiceImpl(SubjectDaoImpl subjectDao, SubjectMapper subjectMapper) {
        super(subjectDao, subjectMapper, SubjectNotFound.class);
    }
}
