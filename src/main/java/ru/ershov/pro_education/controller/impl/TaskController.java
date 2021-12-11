package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.TaskDto;
import ru.ershov.pro_education.service.impl.TaskServiceImpl;

@RestController
@RequestMapping("/api/task")
public class TaskController extends AbstractController<TaskDto, Long> {
    TaskController(TaskServiceImpl taskService) {
        super(taskService);
    }
}
