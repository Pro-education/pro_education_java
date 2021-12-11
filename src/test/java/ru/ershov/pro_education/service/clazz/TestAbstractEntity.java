package ru.ershov.pro_education.service.clazz;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.AfterEach;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;
import ru.ershov.pro_education.entity.AbstractEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@Table(name = "test")
@EqualsAndHashCode(callSuper = false)
public class TestAbstractEntity extends AbstractEntity {

    @Column(name = "field")
    private String field;
}
