package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;

@Getter
@Setter
@Table(name = "role")
public class Role extends AbstractEntity {
    @Column(name = "name")
    private String name;
}
