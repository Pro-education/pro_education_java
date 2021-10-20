package ru.ershov.pro_education.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.UsersDaoImpl;
import ru.ershov.pro_education.entity.User;
import ru.ershov.pro_education.exception.InstituteNotFound;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImpl implements Crud<User, Long> {

    private final UsersDaoImpl usersDao;

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

}
