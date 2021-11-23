package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@Table(name = "users__role")
public class UserRole extends AbstractEntity {

    @ManyToOne(clazz = Role.class)
    @Column(name = "role_id")
    private Long roleId;

    @ManyToOne(clazz = User.class)
    @Column(name = "user_id")
    private Long userId;
}
