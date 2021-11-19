package ru.ershov.pro_education.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.service.impl.InstituteImpl;

@Controller
@RequestMapping("/institute")
public class InstituteController extends AbstractController<InstituteDto, Long> {

    public InstituteController(InstituteImpl crud) {
        super(crud);
    }

}
