package ru.ershov.pro_education.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.config.jwt.TokenProvider;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.service.impl.PersonServiceImpl;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;
    private final PersonServiceImpl userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        Person p = new Person();
        p.setPassword(registrationRequest.getPassword());
        p.setEmail(registrationRequest.getLogin());
        p.setEnabled(true);
        userService.insert(p);
        return "OK";
    }

    @PostMapping(value = "/authenticate")
    public String generateToken(@RequestBody Person loginPerson) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginPerson.getEmail(),
                        loginPerson.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(authentication);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value="/userping") // test
    public String userPing(){
        return "Any User Can Read This";
    }

}
