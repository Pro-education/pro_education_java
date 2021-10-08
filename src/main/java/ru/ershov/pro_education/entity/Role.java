package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class Role extends AbstractEntity {

    private String name;

    private Set<UserRole> users;

    public String getAuthority() {
        return name;
    }
}
