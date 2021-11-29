package ru.ershov.pro_education.entity;

import lombok.*;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "homework")
@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Homework extends AbstractEntity{

    @ManyToOne(clazz = Team.class)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "name")
    private String name;
}
