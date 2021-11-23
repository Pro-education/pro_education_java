package ru.ershov.pro_education.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.UsersDaoImpl;
import ru.ershov.pro_education.entity.User;
import ru.ershov.pro_education.exception.InstituteNotFound;
import ru.ershov.pro_education.service.CrudService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements CrudService<User, Long>, UserDetailsService {

    private final UsersDaoImpl usersDao;
    private final RoleServiceImpl roleService;

    @Override
    public User findById(Long id) {
        return usersDao.findById(id).orElseThrow(() -> new InstituteNotFound(id));
    }

    @Override
    public List<User> findAll() {
        return usersDao.findAll();
    }

    @Override
    public <S extends User> S insert(S entity) {
        return usersDao.insert(entity);
    }

    @Override
    public <S extends User> S update(Long id, S newEntity) {
        return usersDao.update(id, newEntity);
    }

    @Override
    public boolean existById(Long id) {
        return usersDao.existById(id);
    }

    @Override
    public boolean delete(Long aLong) {
        return usersDao.delete(aLong);
    }

    public User getUser(String email) {
        return usersDao.findByEmail(email).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email - " + email + " not found."));
        Set<SimpleGrantedAuthority> rolesByUserId = roleService.getRolesByUserId(user.getId());
        System.out.println(rolesByUserId);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true, true, true,
                true, rolesByUserId);
    }
}
