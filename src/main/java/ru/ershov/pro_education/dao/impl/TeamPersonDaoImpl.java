package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.*;

import java.util.List;

@Repository
public class TeamPersonDaoImpl extends AbstractDao<TeamPerson, Long> {

    TeamPersonDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TeamPerson.class);
    }

    public List<TeamPerson> findAllByPersonId(Long personId){
        return getAllFromParent(personId, Person.class);
    }

    public List<TeamPerson> findAllByTeamId(Long teamId){
        return getAllFromParent(teamId, Team.class);
    }
}
