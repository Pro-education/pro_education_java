package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.HomeworkDto;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.dto.TeamDto;
import ru.ershov.pro_education.service.impl.HomeworkServiceImpl;
import ru.ershov.pro_education.service.impl.TeamPersonServiceImpl;
import ru.ershov.pro_education.service.impl.TeamServiceImpl;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/team")
public class TeamController extends AbstractController<TeamDto, Long> {
    private final TeamPersonServiceImpl teamPersonService;
    private final HomeworkServiceImpl homeworkService;

    TeamController(
            TeamServiceImpl teamService,
            TeamPersonServiceImpl teamPersonService,
            HomeworkServiceImpl homeworkService) {
        super(teamService);
        this.teamPersonService = teamPersonService;
        this.homeworkService = homeworkService;
    }

    @PostMapping("/{teamId}/person/{personId}")
    public void insertDepartment(
            @PathVariable("teamId") Long teamId,
            @PathVariable("personId") Long personId
    ) {
        teamPersonService.connect(teamId, personId);
    }

    @PutMapping("/{teamId}/headman/{personId}")
    public TeamDto setHeadman(
            @PathVariable("teamId") Long teamId,
            @PathVariable("personId") Long personId
    ) {
        return teamPersonService.connectHeadman(teamId, personId);
    }

    @GetMapping("/{id}/homework")
    public List<HomeworkDto> homeworks(@PathVariable("id") Long id) {
        return homeworkService.findAllByTeamId(id);
    }


}
