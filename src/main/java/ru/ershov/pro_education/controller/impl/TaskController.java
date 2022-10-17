package ru.ershov.pro_education.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.TaskDto;
import ru.ershov.pro_education.service.impl.TaskServiceImpl;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/task")
public class TaskController extends AbstractController<TaskDto, Long> {

    private final TaskServiceImpl taskService;

    TaskController(TaskServiceImpl taskService) {
        super(taskService);
        this.taskService = taskService;
    }
}
