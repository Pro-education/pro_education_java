package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@ToString
@Table(name = "direction")
public class Direction extends AbstractEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "number")
    private String number;
    // One to one with reviews?
    @Column(name = "reviews_id")
    private Long reviewsId;
    @Column(name = "vk_link")
    private String vkLink;
}
