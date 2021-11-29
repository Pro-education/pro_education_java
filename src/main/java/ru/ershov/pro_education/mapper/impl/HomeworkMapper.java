package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.HomeworkDto;
import ru.ershov.pro_education.entity.Homework;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class HomeworkMapper extends AbstractMapper<Homework, HomeworkDto> {
    HomeworkMapper() {
        super(Homework.class, HomeworkDto.class);
    }
}
