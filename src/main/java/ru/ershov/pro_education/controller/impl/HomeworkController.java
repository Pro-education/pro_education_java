package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.HomeworkDto;
import ru.ershov.pro_education.dto.TaskDto;
import ru.ershov.pro_education.service.impl.HomeworkServiceImpl;
import ru.ershov.pro_education.service.impl.TaskServiceImpl;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/homework")
public class HomeworkController extends AbstractController<HomeworkDto, Long> {

    private final HomeworkServiceImpl homeworkService;
    private final TaskServiceImpl taskService;

    HomeworkController(HomeworkServiceImpl homeworkService, TaskServiceImpl taskService) {
        super(homeworkService);
        this.homeworkService = homeworkService;
        this.taskService = taskService;
    }

    @GetMapping("/{id}/task")
    public List<TaskDto> findAllTaskByHomeWorkId(
            @PathVariable("id") Long homeworkId
    ) {
        return taskService.findAllByHomework(homeworkId);
    }

    @GetMapping("/{id}/add/{personId}")
    public void addMyHomework (
            @PathVariable("id") Long homeworkId,
            @PathVariable("personId") Long personId
    ) {
        homeworkService.addMeHomework(homeworkId, personId);
    }

}
