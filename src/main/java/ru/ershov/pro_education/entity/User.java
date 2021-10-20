package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Table(name = "user")
@Getter
@Setter
public class User extends AbstractEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Short enabled;

}
