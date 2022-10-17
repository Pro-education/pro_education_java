package ru.ershov.pro_education.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Id;
import ru.ershov.pro_education.annotation.ManyToOne;

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

    @Column(name = "check_status")
    private String checkStatus;

    @Column(name = "approver_id")
    @ManyToOne(clazz = Person.class)
    private Long approverId;

}
