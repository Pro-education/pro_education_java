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
@Table(name = "direction")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Direction extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "vk_link")
    private String vkLink;

    @ManyToOne(clazz = Department.class)
    @Column(name = "department_id")
    private Long departmentId;
}
