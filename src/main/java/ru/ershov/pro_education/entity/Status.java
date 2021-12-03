package ru.ershov.pro_education.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@Table(name = "status")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Status extends AbstractEntity {
    @Column(name = "name")
    private String name;
}
