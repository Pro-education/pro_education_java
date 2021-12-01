package ru.ershov.pro_education.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class TeamPersonDto extends AbstractDto {

    @NotEmpty
//    @JsonProperty("team_id")
    private Long teamId;

    @NotEmpty
//    @JsonProperty("person_id")
    private Long personId;
}
