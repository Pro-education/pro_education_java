package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@Table(name = "review__tag")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ReviewTag extends AbstractEntity {

    @ManyToOne(clazz = Review.class)
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne(clazz = Tag.class)
    @Column(name = "tag_id")
    private Long tagId;
}
