package ru.ershov.pro_education.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ershov.pro_education.dao.impl.ReviewTagDaoImpl;
import ru.ershov.pro_education.dto.ReviewDto;
import ru.ershov.pro_education.dto.TagDto;
import ru.ershov.pro_education.entity.ReviewTag;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewTagServiceImpl {

    private final ReviewTagDaoImpl dao;
    private final ReviewServiceImpl reviewService;
    private final TagServiceImpl tagService;


    public void connect(Long reviewId, Long tagId){
        ReviewTag reviewTag = new ReviewTag(reviewId, tagId);
        dao.insert(reviewTag);
    }

    public List<TagDto> findAllTagsByReviewId(Long reviewId){
        return dao.findAllTagsByReviewId(reviewId)
                .stream()
                .map(tagService::findById)
                .collect(Collectors.toList());
    }

    public List<ReviewDto> findAllReviewsByTagId(Long tagId){
        return dao.findAllReviewsByTagId(tagId)
                .stream()
                .map(reviewService::findById)
                .collect(Collectors.toList());
    }

}
