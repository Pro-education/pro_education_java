package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
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
