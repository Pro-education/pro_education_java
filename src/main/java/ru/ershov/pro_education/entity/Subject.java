package ru.ershov.pro_education.entity;

import lombok.*;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Subject extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne(clazz = Direction.class)
    @Column(name = "direction_id")
    private Long directionId;
}
