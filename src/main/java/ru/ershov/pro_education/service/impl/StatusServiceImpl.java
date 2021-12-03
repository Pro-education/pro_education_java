package ru.ershov.pro_education.service.impl;

import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.StatusDaoImpl;
import ru.ershov.pro_education.dto.StatusDto;
import ru.ershov.pro_education.entity.Status;
import ru.ershov.pro_education.exception.not_found.StatusNotFound;
import ru.ershov.pro_education.mapper.impl.StatusMapper;
import ru.ershov.pro_education.service.AbstractCrudService;

@Service
public class StatusServiceImpl extends AbstractCrudService<Status, StatusDto, Long> {
    protected StatusServiceImpl(
            StatusDaoImpl dao,
            StatusMapper mapper
    ) {
        super(dao, mapper, StatusNotFound.class);
    }
}
