package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.TeamDto;
import ru.ershov.pro_education.service.impl.TeamServiceImpl;

@RestController
@RequestMapping("/group")
public class TeamController extends AbstractController<TeamDto, Long> {
    TeamController(TeamServiceImpl groupService) {
        super(groupService);
    }
}
