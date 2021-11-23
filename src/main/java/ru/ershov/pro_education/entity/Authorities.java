package ru.ershov.pro_education.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Table(name = "authorities")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Authorities extends AbstractEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "authorities")
    private String authorities;

}
