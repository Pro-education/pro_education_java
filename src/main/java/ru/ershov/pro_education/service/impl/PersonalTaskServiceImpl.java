package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.PersonalTaskDaoImpl;
import ru.ershov.pro_education.dto.PersonalTaskDto;
import ru.ershov.pro_education.entity.PersonalTask;
import ru.ershov.pro_education.exception.not_found.PersonalTaskNotFound;
import ru.ershov.pro_education.mapper.impl.PersonalTaskMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class PersonalTaskServiceImpl extends AbstractCrudService<PersonalTask, PersonalTaskDto, Long> {
    protected PersonalTaskServiceImpl(PersonalTaskDaoImpl personalTaskDao,
                                      PersonalTaskMapper personalTaskMapper) {
        super(personalTaskDao, personalTaskMapper, PersonalTaskNotFound.class);
    }
}
