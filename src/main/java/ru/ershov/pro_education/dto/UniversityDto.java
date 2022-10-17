package ru.ershov.pro_education.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class UniversityDto extends AbstractDto {

    @NotBlank(message = "The name field should not be empty")
    private String name;

    @NotBlank
    private String fullName;

    @NotBlank
    private String vkLink;

    @Null
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long owner;
}
