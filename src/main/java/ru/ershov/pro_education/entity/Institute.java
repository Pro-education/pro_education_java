package ru.ershov.pro_education.entity;

import lombok.*;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "institute")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Institute extends AbstractEntity {

    @Column(name = "short_name")
    private String name;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "vk_link")
    private String vkLink;

    @ManyToOne(clazz = University.class)
    @Column(name = "university_id")
    private Long universityId;
}
