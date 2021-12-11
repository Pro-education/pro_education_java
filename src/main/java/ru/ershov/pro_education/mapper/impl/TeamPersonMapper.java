package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.TeamPersonDto;
import ru.ershov.pro_education.entity.TeamPerson;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class TeamPersonMapper extends AbstractMapper<TeamPerson, TeamPersonDto> {
    TeamPersonMapper() {
        super(TeamPerson.class, TeamPersonDto.class);
    }
}
