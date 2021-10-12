package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Setter
@Getter
@ToString
@Table(name = "university")
public class University extends AbstractEntity {

    @Column(name = "name")
    private String name;

}
