package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@Table(name = "person__role")
public class PersonRole extends AbstractEntity {

    @ManyToOne(clazz = Role.class)
    @Column(name = "role_id")
    private Long roleId;

    @ManyToOne(clazz = Person.class)
    @Column(name = "person_id")
    private Long personId;
}
