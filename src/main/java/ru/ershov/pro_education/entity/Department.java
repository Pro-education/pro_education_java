package ru.ershov.pro_education.entity;

import lombok.*;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Department extends AbstractEntity {

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "vk_link")
    private String vkLink;
}
