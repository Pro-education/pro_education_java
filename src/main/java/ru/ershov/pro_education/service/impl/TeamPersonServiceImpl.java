package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.TeamPersonDaoImpl;
import ru.ershov.pro_education.dto.TeamDto;
import ru.ershov.pro_education.dto.TeamPersonDto;
import ru.ershov.pro_education.entity.TeamPerson;
import ru.ershov.pro_education.exception.not_found.TeamPersonException;
import ru.ershov.pro_education.mapper.impl.TeamPersonMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class TeamPersonServiceImpl extends AbstractCrudService<TeamPerson, TeamPersonDto, Long> {

    private final TeamPersonDaoImpl teamPersonDao;
    private final TeamServiceImpl teamService;
    private final PersonServiceImpl personService;

    TeamPersonServiceImpl(
            TeamPersonDaoImpl teamPersonDao,
            TeamPersonMapper teamPersonMapper,
            TeamServiceImpl teamService,
            PersonServiceImpl personService
    ) {
        super(teamPersonDao, teamPersonMapper, TeamPersonException.class);
        this.teamPersonDao = teamPersonDao;
        this.teamService = teamService;
        this.personService = personService;
    }

    public void connect(Long teamId, Long personId) {
        TeamPerson teamPerson = new TeamPerson(teamId, personId);
        teamPersonDao.insert(teamPerson);
    }

    public TeamDto connectHeadman(Long teamId, Long personId) {
        TeamDto team = teamService.findById(teamId);
        if (personService.existById(personId)) {
            System.out.println(personId);
            team.setHeadman(personId);
        }
        System.out.println(team);
        return teamService.update(team.getId(), team);
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
