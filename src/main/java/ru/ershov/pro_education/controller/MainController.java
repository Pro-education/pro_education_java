package ru.ershov.pro_education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ershov.pro_education.entity.University;
import ru.ershov.pro_education.service.CrudService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CrudService<University> crudService;

    @GetMapping("/")
    public String createUser() {
        return "create-user";
    }

    @PostMapping("/")
    public String createUserPost(
            @RequestParam(defaultValue = "1") String username,
            @RequestParam(defaultValue = "1") String password
    ) {
        return "redirect:/login";
    }

    @GetMapping("/university")
    public ResponseEntity<List<University>> getAllUniversities() {
        return ResponseEntity.ok(crudService.findAll());
    }

    @GetMapping("/university/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable("id") Long id) {
        return ResponseEntity.ok(crudService.findById(id));
    }

    @GetMapping("/1")
    public ResponseEntity<University> test() {
        University university = new University();
        university.setId(12L);
        university.setName("haha");
        return ResponseEntity.ok(crudService.save(university));
    }

    @GetMapping("/i")
    public String i() {
        return "i";
    }


    @GetMapping( "/logout")
    public String logout(
            HttpServletRequest request
    ) {
        request.getSession(true).invalidate();

        return "redirect:/login";
    }

}
