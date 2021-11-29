package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.TeamDaoImpl;
import ru.ershov.pro_education.dto.TeamDto;
import ru.ershov.pro_education.entity.Team;
import ru.ershov.pro_education.exception.not_found.GroupNotFound;
import ru.ershov.pro_education.mapper.impl.TeamMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class TeamServiceImpl extends AbstractCrudService<Team, TeamDto, Long> {
    TeamServiceImpl(TeamDaoImpl teamDao,
                    TeamMapper teamMapper) {
        super(teamDao, teamMapper, GroupNotFound.class);
    }
}
