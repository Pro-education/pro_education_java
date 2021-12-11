package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.TeamPersonDaoImpl;
import ru.ershov.pro_education.dto.*;
import ru.ershov.pro_education.entity.TeamPerson;
import ru.ershov.pro_education.exception.not_found.TeamPersonException;
import ru.ershov.pro_education.mapper.impl.TeamPersonMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamPersonServiceImpl extends AbstractCrudService<TeamPerson, TeamPersonDto, Long> {

    private final TeamPersonDaoImpl teamPersonDao;

    TeamPersonServiceImpl(TeamPersonDaoImpl teamPersonDao,
                          TeamPersonMapper teamPersonMapper) {
        super(teamPersonDao, teamPersonMapper, TeamPersonException.class);
        this.teamPersonDao = teamPersonDao;
    }

    public void connect(Long teamId, Long personId){
        TeamPerson teamPerson = new TeamPerson(teamId, personId);
        teamPersonDao.insert(teamPerson);
    }

//    public List<TeamDto> findAllByPersonId(Long personId){
//        return teamPersonDao.findAllByPersonId(personId)
//                .stream()
//                .map(teamPerson -> teamService.findById(teamPerson.getTeamId()))
//                .collect(Collectors.toList());
//    }
//
//    public List<PersonDto> findAllByTeamId(Long teamId){
//        return teamPersonDao.findAllByTeamId(teamId)
//                .stream()
//                .map(teamPerson -> personService.findById(teamPerson.getPersonId()))
//                .collect(Collectors.toList());
}
