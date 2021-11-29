package ru.ershov.pro_education.entity;

import lombok.*;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "team")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Team extends AbstractEntity{

    @ManyToOne(clazz = Person.class)
    @Column(name = "headman_id")
    //id
    private Long headmanId;

    @ManyToOne(clazz = Direction.class)
    @Column(name = "direction_id")
    private Long directionId;

    @Column(name = "name")
    private String name;

    @Column(name = "vk_link")
    private String vkLink;
}
