package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Table(name = "users")
@Getter
@Setter
public class Role extends AbstractEntity {
    @Column(name = "name")
    private String name;
}
