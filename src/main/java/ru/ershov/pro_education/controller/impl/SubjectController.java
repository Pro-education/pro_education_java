package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.SubjectDto;
import ru.ershov.pro_education.service.impl.SubjectServiceImpl;

@RestController
@RequestMapping("/api/subject")
public class SubjectController extends AbstractController<SubjectDto, Long> {
    SubjectController(SubjectServiceImpl subjectService) {
        super(subjectService);
    }
}
