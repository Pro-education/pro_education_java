package ru.ershov.pro_education.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.dto.ReviewDto;
import ru.ershov.pro_education.dto.TagDto;
import ru.ershov.pro_education.service.impl.ReviewTagServiceImpl;
import ru.ershov.pro_education.service.impl.TagServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController  {
    private final TagServiceImpl tagService;
    private final ReviewTagServiceImpl reviewTagService;

    @GetMapping()
    public List<TagDto> findAll() {
        return tagService.findAll();
    }

    @GetMapping("/{tagId}/tags")
    public List<ReviewDto> findAllTags(@PathVariable("tagId") Long tagId) {
        return reviewTagService.findAllReviewsByTagId(tagId);
    }

    @PostMapping("/{tagId}/tags/{reviewId}")
    public void connect(@PathVariable("tagId") Long tagId, @PathVariable("reviewId") Long reviewId) {
        reviewTagService.connect(reviewId, tagId);
    }
}
