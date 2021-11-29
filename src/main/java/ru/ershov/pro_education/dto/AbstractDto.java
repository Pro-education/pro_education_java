package ru.ershov.pro_education.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import ru.ershov.pro_education.annotation.Column;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.sql.Timestamp;

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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

//    @Null
//    @JsonProperty("updated_time")
//    private Timestamp updatedTime;
//
//    @Null
//    @JsonProperty("create_time")
//    private Timestamp createTime;
}
