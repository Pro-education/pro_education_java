package ru.ershov.pro_education.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.entity.Role;
import ru.ershov.pro_education.entity.User;
import ru.ershov.pro_education.entity.UserRole;

import java.util.List;

@Repository
public class UserRoleDaoImpl extends AbstractDao<UserRole, Long> {

    UserRoleDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, UserRole.class);
    }

    public List<UserRole> findRoleIdsByUserId(Long userId) {
        return getAllFromParent(userId, Role.class);
    }

    public List<UserRole> getUserIdsByRoleId(Long roleId) {
        return getAllFromParent(roleId, User.class);
    }
}