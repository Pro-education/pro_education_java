package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.StatusDto;
import ru.ershov.pro_education.entity.Status;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class StatusMapper extends AbstractMapper<Status, StatusDto> {
    protected StatusMapper() {
        super(Status.class, StatusDto.class);
    }
}
