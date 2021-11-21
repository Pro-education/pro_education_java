package ru.ershov.pro_education.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.ershov.pro_education.annotation.Column;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class InstituteDto extends AbstractDto {

    @NotBlank(message = "The name field should not be empty")
    private String name;

    @NotBlank
    private String fullName;

    @NotBlank
    private String vkLink;

    @NotEmpty
    private Long universityId;
}
