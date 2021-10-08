package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity  {

    private String username;
    private String password;

    private List<UserRole> roles;

}
