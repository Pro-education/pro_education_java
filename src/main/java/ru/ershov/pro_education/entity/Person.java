package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Table(name = "person")
@Getter
@Setter
public class Person extends AbstractEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "vk_link")
    private String vkLink;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "rating_sum")
    private String ratingSum;

    @Column(name = "rating_count")
    private String ratingCount;

}
