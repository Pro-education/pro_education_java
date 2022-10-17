package ru.ershov.pro_education.controller.impl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.pro_education.controller.AbstractController;
import ru.ershov.pro_education.dto.DiscussionDto;
import ru.ershov.pro_education.dto.ReviewDto;
import ru.ershov.pro_education.dto.TagDto;
import ru.ershov.pro_education.service.impl.DiscussionServiceImpl;
import ru.ershov.pro_education.service.impl.ReviewServiceImpl;
import ru.ershov.pro_education.service.impl.ReviewTagServiceImpl;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reviews")
public class ReviewController extends AbstractController<ReviewDto, Long> {

    private final DiscussionServiceImpl discussionService;
    private final ReviewTagServiceImpl reviewTagService;

    protected ReviewController(
            ReviewServiceImpl service,
            DiscussionServiceImpl discussionService,
            ReviewTagServiceImpl reviewTagService
    ) {
        super(service);
        this.discussionService = discussionService;
        this.reviewTagService = reviewTagService;
    }

    @GetMapping("/{reviewId}/discussion")
    public List<DiscussionDto> findAllDiscussion(@PathVariable("reviewId") Long reviewId) {
        return discussionService.getAllByReviewId(reviewId);
    }

    @PostMapping("/{reviewId}/discussion")
    public void addTag(@PathVariable("reviewId") Long reviewId, @RequestBody DiscussionDto discussionDto) {
        discussionDto.setReviewId(reviewId);
        discussionService.insert(discussionDto);
    }

    @GetMapping("/{reviewId}/tags")
    public List<TagDto> findAllTags(@PathVariable("reviewId") Long reviewId) {
        return reviewTagService.findAllTagsByReviewId(reviewId);
    }

    @PostMapping("/{reviewId}/tags/{tagId}")
    public void addTag(@PathVariable("reviewId") Long reviewId, @PathVariable("tagId") Long tagId) {
        reviewTagService.connect(reviewId, tagId);
    }
}
