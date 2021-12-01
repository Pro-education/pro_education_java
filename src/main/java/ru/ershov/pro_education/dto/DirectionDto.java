package ru.ershov.pro_education.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class DirectionDto extends AbstractDto{

    @NotBlank
    private String name;

    @NotBlank
    private String number;

    @NotEmpty
    private Long departmentId;

    @NotBlank
    private String vkLink;
}
