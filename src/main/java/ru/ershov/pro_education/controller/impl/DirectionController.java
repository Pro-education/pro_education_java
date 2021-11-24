package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.DirectionDto;
import ru.ershov.pro_education.service.impl.DirectionServiceImpl;

@RestController
@RequestMapping("/api/direction")
public class DirectionController extends AbstractController<DirectionDto, Long> {

    public DirectionController(DirectionServiceImpl directionService) {
        super(directionService);
    }
}
