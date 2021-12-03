package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.DiscussionDto;
import ru.ershov.pro_education.service.impl.DiscussionServiceImpl;

@RestController
@RequestMapping("/api/discussion")
public class DiscussionController extends AbstractController<DiscussionDto, Long> {

    DiscussionController(DiscussionServiceImpl service) {
        super(service);
    }
}
