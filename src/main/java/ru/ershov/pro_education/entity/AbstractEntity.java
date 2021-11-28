package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Id;

import java.sql.Timestamp;


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


    @Column(name = "updated_time", onlyRead = true)
    private Timestamp updatedTime;

    @Column(name = "created_time", onlyRead = true)
    private Timestamp createTime;

}
