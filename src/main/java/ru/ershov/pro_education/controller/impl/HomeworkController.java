package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.HomeworkDto;
import ru.ershov.pro_education.service.impl.HomeworkServiceImpl;

@RestController
@RequestMapping("/api/homework")
public class HomeworkController extends AbstractController<HomeworkDto, Long> {
    HomeworkController(HomeworkServiceImpl homeworkService) {
        super(homeworkService);
    }
}
