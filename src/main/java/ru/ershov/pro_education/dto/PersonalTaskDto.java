package ru.ershov.pro_education.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class PersonalTaskDto extends AbstractDto {

    @NotEmpty
    private Long taskId;

    @NotEmpty
    private Long userId;

    @NotBlank
    private Boolean isSolved;

}
