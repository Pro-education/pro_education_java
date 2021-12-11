package ru.ershov.pro_education.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.PersonDaoImpl;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.exception.not_found.InstituteNotFound;
import ru.ershov.pro_education.service.CrudService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements CrudService<Person, Long>, UserDetailsService {

    private final PersonDaoImpl personDao;
    private final RoleServiceImpl roleService;

    @Override
    public Person findById(Long id) {
        return personDao.findById(id).orElseThrow(() -> new InstituteNotFound(id));
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public <S extends Person> S insert(S entity) {
        return personDao.insert(entity);
    }

    @Override
    public <S extends Person> S update(Long id, S newEntity) {
        return personDao.update(id, newEntity);
    }

    @Override
    public boolean existById(Long id) {
        return personDao.existById(id);
    }

    @Override
    public boolean delete(Long aLong) {
        return personDao.delete(aLong);
    }

    public Person getUser(String email) {
        return personDao.findByEmail(email).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email - " + email + " not found."));
        Set<SimpleGrantedAuthority> rolesByUserId = roleService.getRolesByUserId(person.getId());
        System.out.println(rolesByUserId);
        return new org.springframework.security.core.userdetails.User(
                person.getEmail(), person.getPassword(), true, true, true,
                true, rolesByUserId);
    }
}
