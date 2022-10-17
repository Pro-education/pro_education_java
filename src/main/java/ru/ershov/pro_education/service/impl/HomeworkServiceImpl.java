package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.HomeworkDaoImpl;
import ru.ershov.pro_education.dto.HomeworkDto;
import ru.ershov.pro_education.entity.Homework;
import ru.ershov.pro_education.exception.not_found.HomeworkNotFound;
import ru.ershov.pro_education.mapper.impl.HomeworkMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeworkServiceImpl extends AbstractCrudService<Homework, HomeworkDto, Long> {
    private final HomeworkDaoImpl homeworkDao;
    private final TaskServiceImpl taskService;

    HomeworkServiceImpl(
            HomeworkDaoImpl homeworkDao,
            HomeworkMapper homeworkMapper,
            TaskServiceImpl taskService
    ) {
        super(homeworkDao, homeworkMapper, HomeworkNotFound.class);
        this.homeworkDao = homeworkDao;
        this.taskService = taskService;
    }

    public List<HomeworkDto> findAllByTeamId(Long id) {
        return homeworkDao.findAllByTeamId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void addMeHomework(Long homeworkId, Long personId) {
        homeworkDao.addMyHomework(homeworkId, personId);
    }
}
