package ru.ershov.pro_education.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.PersonDaoImpl;
import ru.ershov.pro_education.dto.PersonDto;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.entity.Role;
import ru.ershov.pro_education.exception.not_found.PersonNotFound;
import ru.ershov.pro_education.mapper.AbstractMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

import java.util.Set;

@Service
public class PersonServiceImpl extends AbstractCrudService<Person, PersonDto, Long> implements UserDetailsService {

    private final PersonDaoImpl personDao;
    private final RoleServiceImpl roleService;

    protected PersonServiceImpl(
            PersonDaoImpl personDao,
            AbstractMapper<Person, PersonDto> mapper,
            RoleServiceImpl roleService) {
        super(personDao, mapper, PersonNotFound.class);
        this.personDao = personDao;
        this.roleService = roleService;
    }

    public Person getUser(String email) {
        return personDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email - " + email + " not found."));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email - " + email + " not found."));
        Set<Role> rolesByUserId = roleService.getRolesByUserId(person.getId());
        person.setRoles(rolesByUserId);
        return person;
    }
}
