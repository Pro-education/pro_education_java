package ru.ershov.pro_education.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.config.jwt.JwtProvider;
import ru.ershov.pro_education.entity.User;
import ru.ershov.pro_education.service.impl.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setEmail(registrationRequest.getLogin());
        u.setRoleId(1L);
        u.setEnabled(true);
        userService.insert(u);
        return "OK";
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody AuthRequest request) {
        User user = userService.getUser(request.getLogin());
        String body = jwtProvider.generateToken(user.getEmail());
        return ResponseEntity.ok(body);
    }

}
