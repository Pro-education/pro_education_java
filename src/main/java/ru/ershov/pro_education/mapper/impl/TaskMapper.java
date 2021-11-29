package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.TaskDto;
import ru.ershov.pro_education.entity.Task;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class TaskMapper extends AbstractMapper<Task, TaskDto> {
    protected TaskMapper() {
        super(Task.class, TaskDto.class);
    }
}
