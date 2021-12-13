package ru.ershov.pro_education.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ReviewDto extends AbstractDto {

    @NotEmpty
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long personId;
    @NotEmpty
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String status;
    @NotEmpty
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long tableId;
    @NotBlank
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String tableName;
    @NotBlank
    private String text;
    @NotBlank
    private Integer rating;
}
