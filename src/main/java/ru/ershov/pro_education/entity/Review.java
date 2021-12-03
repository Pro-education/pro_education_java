package ru.ershov.pro_education.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@Table(name = "review")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Review extends AbstractEntity {

    @Column(name="person_id")
    private Long personId;
    @Column(name="status_id")
    private Long statusId;
    @Column(name="table_id")
    private Long tableId;
    @Column(name="table_name")
    private String tableName;
    @Column(name="text")
    private String text;
    @Column(name="rating")
    private Integer rating;
}
