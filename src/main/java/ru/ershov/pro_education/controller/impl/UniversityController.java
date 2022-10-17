package ru.ershov.pro_education.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractWithReviewController;
import ru.ershov.pro_education.dto.InstituteDto;
import ru.ershov.pro_education.dto.UniversityDto;
import ru.ershov.pro_education.service.impl.UniversityServiceImpl;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/university")
public class UniversityController extends AbstractWithReviewController<UniversityDto, Long> {

    private final UniversityServiceImpl universityService;

    public UniversityController(UniversityServiceImpl service) {
        super(service);
        this.universityService = service;
    }

    @GetMapping("/{id}/institutes")
    public List<InstituteDto> institutes(@PathVariable("id") Long id) {
        return universityService.findAllInstitutes(id);
    }

    @PutMapping("/{universityId}/owner/{ownerId}")
    public UniversityDto institutes(
            @PathVariable("universityId") Long universityId,
            @PathVariable("ownerId") Long ownerId
    ) {
        return universityService.connectOwner(universityId, ownerId);
    }

}
