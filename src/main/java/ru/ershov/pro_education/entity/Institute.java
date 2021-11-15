package ru.ershov.pro_education.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@Table(name = "institutes")
public class Institute extends AbstractEntity {

    @Column(name = "name")
    private String name;

//    @Column(name = "university_id")
//    private Long universityId;
}
