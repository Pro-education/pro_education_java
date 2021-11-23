package ru.ershov.pro_education.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.RoleDaoImpl;
import ru.ershov.pro_education.dao.impl.UserRoleDaoImpl;
import ru.ershov.pro_education.entity.UserRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {
    private final RoleDaoImpl roleDao;
    private final UserRoleDaoImpl userRoleDao;

    public Set<SimpleGrantedAuthority> getRolesByUserId(Long userId) {
        List<UserRole> roleIdsByUserId = userRoleDao.findRoleIdsByUserId(userId);
        Set<SimpleGrantedAuthority> roles = new HashSet<>(roleIdsByUserId.size());
        for (UserRole userRole : roleIdsByUserId) {
            roles.add(new SimpleGrantedAuthority(roleDao.findById(userRole.getRoleId()).orElseThrow().getName()));
        }
        return roles;
    }
}
