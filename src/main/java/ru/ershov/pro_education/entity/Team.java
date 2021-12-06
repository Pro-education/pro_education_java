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
