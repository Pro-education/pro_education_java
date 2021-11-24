package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
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
@Table(name = "institute_department")
public class InstituteDepartment extends AbstractEntity{

    @ManyToOne(clazz = Institute.class)
    @Column(name = "institute_id")
    private Long instituteId;

    @ManyToOne(clazz = Department.class)
    @Column(name = "department_id")
    private Long departmentId;

}
