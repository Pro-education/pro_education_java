package ru.ershov.pro_education.controller;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
