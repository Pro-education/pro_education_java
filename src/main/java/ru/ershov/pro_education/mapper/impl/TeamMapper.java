package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.TeamDto;
import ru.ershov.pro_education.entity.Team;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class TeamMapper extends AbstractMapper<Team, TeamDto> {
    TeamMapper() {
        super(Team.class, TeamDto.class);
    }
}
