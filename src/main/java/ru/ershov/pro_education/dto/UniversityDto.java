package ru.ershov.pro_education.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.ershov.pro_education.annotation.Column;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Validated
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class UniversityDto extends AbstractDto {

    @NotBlank(message = "The name field should not be empty")
    private String name;

    private String fullName;

    private String vkLink;
}
