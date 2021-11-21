package ru.ershov.pro_education.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.service.impl.InstituteServiceImpl;

@Controller
@RequestMapping("/institute")
public class InstituteController extends AbstractController<InstituteDto, Long> {

    public InstituteController(InstituteServiceImpl crud) {
        super(crud);
    }

}
