package ru.ershov.pro_education.service.clazz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Table;
import ru.ershov.pro_education.dto.AbstractDto;

@Getter
@Setter
@NoArgsConstructor
public class TestAbstractDto extends AbstractDto {
    private String field;
}
