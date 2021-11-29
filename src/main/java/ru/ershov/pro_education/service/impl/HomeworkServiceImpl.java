package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.HomeworkDaoImpl;
import ru.ershov.pro_education.dto.HomeworkDto;
import ru.ershov.pro_education.entity.Homework;
import ru.ershov.pro_education.exception.not_found.HomeworkNotFound;
import ru.ershov.pro_education.mapper.impl.HomeworkMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class HomeworkServiceImpl extends AbstractCrudService<Homework, HomeworkDto, Long> {
    HomeworkServiceImpl(HomeworkDaoImpl homeworkDao,
                        HomeworkMapper homeworkMapper) {
        super(homeworkDao, homeworkMapper, HomeworkNotFound.class);
    }
}
