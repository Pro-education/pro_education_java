package ru.ershov.pro_education.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@Table(name = "tag")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Tag extends AbstractEntity {

    @Column(name = "text")
    private String text;
    @Column(name = "color")
    private String color;

}
