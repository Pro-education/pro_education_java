package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.PersonalTaskDto;
import ru.ershov.pro_education.service.impl.PersonalTaskServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/personal_task")
public class PersonalTaskController extends AbstractController<PersonalTaskDto, Long> {

    private final PersonalTaskServiceImpl personalTaskService;

    PersonalTaskController(PersonalTaskServiceImpl personalTaskService) {
        super(personalTaskService);
        this.personalTaskService = personalTaskService;
    }

    @GetMapping("/my/{personId}")
    public List<PersonalTaskDto> getMy(
            @PathVariable("personId") Long personId
    ) {
        return personalTaskService.findAll().stream()
                .filter(personalTaskDto -> personalTaskDto.getPersonId().equals(personId))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/solved")
    public void solved(
            @PathVariable("id") Long id
    ) {
        PersonalTaskDto byId = personalTaskService.findById(id);
        byId.setIsSolved(!byId.getIsSolved());
        personalTaskService.update(id, byId);
    }
}
