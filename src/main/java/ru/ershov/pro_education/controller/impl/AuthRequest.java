package ru.ershov.pro_education.controller.impl;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
