package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.PersonalTaskDto;
import ru.ershov.pro_education.entity.PersonalTask;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class PersonalTaskMapper extends AbstractMapper<PersonalTask, PersonalTaskDto> {
    PersonalTaskMapper() {
        super(PersonalTask.class, PersonalTaskDto.class);
    }
}
