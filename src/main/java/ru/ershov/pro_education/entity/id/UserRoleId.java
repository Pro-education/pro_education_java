package ru.ershov.pro_education.entity.id;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class UserRoleId implements Serializable {
    private long userId;
    private long roleId;
}
