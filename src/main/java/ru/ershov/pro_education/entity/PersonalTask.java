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
@Table(name = "personal_task")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class PersonalTask extends AbstractEntity {

    @ManyToOne(clazz = Task.class)
    @Column(name = "task_id")
    private Long taskId;

    @ManyToOne(clazz = Person.class)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "is_solved")
    private Boolean isSolved;

}
