package ru.ershov.pro_education.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.entity.Person;
import ru.ershov.pro_education.entity.Review;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class DiscussionDto extends AbstractDto {

    @Column(name = "review_id")
    @ManyToOne(clazz = Review.class)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long reviewId;

    @Column(name = "person_id")
    @ManyToOne(clazz = Person.class)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long personId;

    @Column(name = "text")
    private String text;
}
