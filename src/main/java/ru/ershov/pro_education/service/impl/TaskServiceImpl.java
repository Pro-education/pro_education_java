package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.TaskDaoImpl;
import ru.ershov.pro_education.dto.TaskDto;
import ru.ershov.pro_education.entity.Task;
import ru.ershov.pro_education.exception.not_found.TaskNotFound;
import ru.ershov.pro_education.mapper.impl.TaskMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class TaskServiceImpl extends AbstractCrudService<Task, TaskDto, Long> {
    TaskServiceImpl(TaskDaoImpl taskDao,
                    TaskMapper taskMapper) {
        super(taskDao, taskMapper, TaskNotFound.class);
    }
}
