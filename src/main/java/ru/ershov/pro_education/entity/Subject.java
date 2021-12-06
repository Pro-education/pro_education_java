package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Subject extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne(clazz = Direction.class)
    @Column(name = "direction_id")
    private Long directionId;
}
