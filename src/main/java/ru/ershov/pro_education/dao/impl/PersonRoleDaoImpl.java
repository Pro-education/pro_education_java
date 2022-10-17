package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Role;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.entity.PersonRole;

import java.util.List;

@Repository
public class PersonRoleDaoImpl extends AbstractDao<PersonRole, Long> {

    PersonRoleDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, PersonRole.class);
    }

    public List<PersonRole> findRoleIdsByPersonId(Long userId) {
        return getAllFromParent(userId, Person.class);
    }

    public List<PersonRole> getUserIdsByRoleId(Long roleId) {
        return getAllFromParent(roleId, Role.class);
    }
}