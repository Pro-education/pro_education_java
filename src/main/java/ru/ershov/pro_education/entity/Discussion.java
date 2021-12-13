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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discussion")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Discussion extends AbstractEntity {

    @ManyToOne(clazz = Review.class)
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne(clazz = Person.class)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "text")
    private String text;
}
