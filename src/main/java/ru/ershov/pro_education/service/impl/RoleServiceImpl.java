package ru.ershov.pro_education.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.PersonRoleDaoImpl;
import ru.ershov.pro_education.dao.impl.RoleDaoImpl;
import ru.ershov.pro_education.entity.PersonRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {
    private final RoleDaoImpl roleDao;
    private final PersonRoleDaoImpl personRoleDao;

    public Set<SimpleGrantedAuthority> getRolesByUserId(Long userId) {
        List<PersonRole> roleIdsByPersonId = personRoleDao.findRoleIdsByPersonId(userId);
        Set<SimpleGrantedAuthority> roles = new HashSet<>(roleIdsByPersonId.size());
        for (PersonRole personRole : roleIdsByPersonId) {
            roles.add(new SimpleGrantedAuthority(roleDao.findById(personRole.getRoleId()).orElseThrow().getName()));
        }
        return roles;
    }
}
