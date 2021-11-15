package ru.ershov.pro_education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.config.jwt.JwtProvider;
import ru.ershov.pro_education.entity.User;
import ru.ershov.pro_education.service.UserImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserImpl userService;
    private final JwtProvider jwtProvider;

//    @PostMapping("/register")
//    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
//        UserEntity u = new UserEntity();
//        u.setPassword(registrationRequest.getPassword());
//        u.setLogin(registrationRequest.getLogin());
//        userService.saveUser(u);
//        return "OK";
//    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody AuthRequest request) {
        User user = userService.getUser(request.getLogin());
        String body = jwtProvider.generateToken(user.getEmail(), user.getPassword());
        System.out.println(body);
        return ResponseEntity.ok(body);
    }

}
