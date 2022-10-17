package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.TeamDaoImpl;
import ru.ershov.pro_education.dto.HomeworkDto;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.dto.TeamDto;
import ru.ershov.pro_education.entity.Team;
import ru.ershov.pro_education.entity.TeamPerson;
import ru.ershov.pro_education.exception.not_found.GroupNotFound;
import ru.ershov.pro_education.mapper.impl.TeamMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;

@Service
public class TeamServiceImpl extends AbstractCrudService<Team, TeamDto, Long> {
    private final HomeworkServiceImpl homeworkService;
    TeamServiceImpl(TeamDaoImpl teamDao,
                    TeamMapper teamMapper,
                    HomeworkServiceImpl homeworkService) {
        super(teamDao, teamMapper, GroupNotFound.class);
        this.homeworkService = homeworkService;
    }

    public List<HomeworkDto> findAllHomework(Long id) {
        return homeworkService.findAllByTeamId(id);
    }

}
