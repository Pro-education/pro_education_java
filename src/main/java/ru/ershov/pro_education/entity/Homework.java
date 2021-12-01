package ru.ershov.pro_education.entity;

import lombok.*;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "homework")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Homework extends AbstractEntity{

    @ManyToOne(clazz = Team.class)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "name")
    private String name;
}
