package ru.ershov.pro_education.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class DirectionDto extends AbstractDto{

    @NotBlank
    private String name;

    @NotBlank
    private String number;

    @NotEmpty
    private Long reviewsId;

    @NotBlank
    private String vkLink;
}
