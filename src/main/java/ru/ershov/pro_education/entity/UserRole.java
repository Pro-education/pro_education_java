package ru.ershov.pro_education.entity;

import lombok.Getter;
import ru.ershov.pro_education.entity.id.UserRoleId;

@Getter
public class UserRole {

    private final UserRoleId id = new UserRoleId();

    private User user;

    private Role role;
}
