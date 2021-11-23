package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractEntity {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

}
