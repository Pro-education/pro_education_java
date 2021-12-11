package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "university")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class University extends AbstractEntity {

    @Column(name = "short_name")
    private String name;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "vk_link")
    private String vkLink;

}
