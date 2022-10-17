package ru.ershov.pro_education.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.config.jwt.TokenProvider;
import ru.ershov.pro_education.dao.impl.PersonDaoImpl;
import ru.ershov.pro_education.dto.PersonDto;
import ru.ershov.pro_education.dto.TokenDto;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.service.Status;
import ru.ershov.pro_education.service.impl.PersonServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;
    private final PersonServiceImpl userService;

    @PostMapping("/signup")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        PersonDto p = new PersonDto();
        p.setPassword(registrationRequest.getPassword());
        p.setEmail(registrationRequest.getLogin());
        p.setEnabled(true);
        p.setCheckStatus(Status.OK);
        userService.insert(p);
        return "OK";
    }

    @PostMapping(value = "/signin")
    public TokenDto generateToken(@RequestBody RegistrationRequest loginPerson) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginPerson.getLogin(),
                        loginPerson.getPassword()
                )
        );
        Person principal = (Person) authentication.getPrincipal();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        System.out.println(roles);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Person user = userService.getUser(principal.getEmail());
        return new TokenDto(jwtTokenUtil.generateToken(authentication), user.getId(), user.getUsername(),
                user.getEmail(), roles);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value="/userping") // test
    public String userPing(){
        return "Any User Can Read This";
    }

}
