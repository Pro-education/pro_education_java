package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Setter
@Getter
@Component
@Table(name = "university")
public class University extends AbstractEntity {

    @Column(name = "name")
    private String name;

}
