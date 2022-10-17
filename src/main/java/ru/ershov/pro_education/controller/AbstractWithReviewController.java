package ru.ershov.pro_education.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.ershov.pro_education.dto.AbstractDto;
import ru.ershov.pro_education.dto.ReviewDto;
import ru.ershov.pro_education.entity.AbstractEntity;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.service.AbstractWithReviewService;

import java.util.List;

public abstract class AbstractWithReviewController<D extends AbstractDto, ID extends Number>
        extends AbstractController<D, ID> {

    private final AbstractWithReviewService<? extends AbstractEntity, D, ID> reviewService;

    protected AbstractWithReviewController(AbstractWithReviewService<? extends AbstractEntity, D, ID> service) {
        super(service);
        this.reviewService = service;
    }

    @GetMapping("/{id}/review")
    @Operation(description = "Отдает отзывы объекта по ID")
    public ResponseEntity<List<ReviewDto>> findAllReviews(@PathVariable("id") ID id) {
        return ResponseEntity.ok(reviewService.findAllReviews(id));
    }

    @PostMapping("/{id}/review")
    @Operation(description = "Cоздает отзыв на объекта по ID")
    public ResponseEntity<ReviewDto> createReviews(@PathVariable("id") ID id, @RequestBody ReviewDto reviewDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person) authentication.getPrincipal();
        reviewDto.setPersonId(person.getId());
        return ResponseEntity.ok(reviewService.createReview(id, reviewDto));
    }


}
