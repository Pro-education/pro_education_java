package ru.ershov.pro_education.entity;

import lombok.*;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Task extends AbstractEntity {

    @Column(name = "text")
    private String text;

    @ManyToOne(clazz = Subject.class)
    @Column(name = "subject_id")
    private Long subjectId;

    @ManyToOne(clazz = Homework.class)
    @Column(name = "homework_id")
    private Long homeworkId;

}
