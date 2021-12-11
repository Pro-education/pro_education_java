package ru.ershov.pro_education.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.PersonDto;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.mapper.AbstractMapper;

@Component
public class PersonMapper extends AbstractMapper<Person, PersonDto> {
    PersonMapper() {
        super(Person.class, PersonDto.class);
    }
}
