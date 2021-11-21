package ru.ershov.pro_education.entity;

import lombok.*;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Table(name = "direction")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Direction extends AbstractEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "number")
    private String number;
    @Column(name = "reviews_id")
    private Long reviewsId;
    @Column(name = "vk_link")
    private String vkLink;
}
