package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.TeamDto;
import ru.ershov.pro_education.service.impl.TeamPersonServiceImpl;
import ru.ershov.pro_education.service.impl.TeamServiceImpl;

@RestController
@RequestMapping("/team")
public class TeamController extends AbstractController<TeamDto, Long> {
    private final TeamPersonServiceImpl teamPersonService;

    TeamController(TeamServiceImpl teamService,
                   TeamPersonServiceImpl teamPersonService) {
        super(teamService);
        this.teamPersonService = teamPersonService;
    }

    @PostMapping("/{teamId}/person/{personId}")
    public void insertDepartment(@PathVariable("teamId") Long teamId,
                                 @PathVariable("personId") Long personId){
        teamPersonService.connect(teamId, personId);
    }
}
