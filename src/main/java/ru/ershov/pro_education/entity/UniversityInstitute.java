package ru.ershov.pro_education.entity;

import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Table(name = "university_institute")
public class UniversityInstitute {

    @Column(name = "university_id")
    private Long universityId;

    @Column(name = "institute_id")
    private Long instituteId;
}
