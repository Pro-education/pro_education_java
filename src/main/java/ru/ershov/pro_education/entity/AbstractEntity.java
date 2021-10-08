package ru.ershov.pro_education.entity;

import lombok.*;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractEntity {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

}
