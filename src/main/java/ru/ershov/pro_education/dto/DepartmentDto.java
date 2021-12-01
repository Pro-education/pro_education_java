package ru.ershov.pro_education.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class DepartmentDto extends AbstractDto{

    @NotBlank
    private String shortName;

    @NotBlank
    private String fullName;

    @NotBlank
    private String vkLink;

}
