package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.PersonalTaskDto;
import ru.ershov.pro_education.service.impl.PersonalTaskServiceImpl;

@RestController
@RequestMapping("/personal_task")
public class PersonalTaskController extends AbstractController<PersonalTaskDto, Long> {
    PersonalTaskController(PersonalTaskServiceImpl personalTaskService) {
        super(personalTaskService);
    }
}
