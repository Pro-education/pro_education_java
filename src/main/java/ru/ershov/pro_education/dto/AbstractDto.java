package ru.ershov.pro_education.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Null;
import java.io.Serializable;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractDto implements Serializable {
    @Null // we don't change id
    @JsonProperty("id")
    @EqualsAndHashCode.Include
    private Long id;
}
