package ru.ershov.pro_education.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DirectionDtoTest {

    @Test
    void testEquals_DifferentFieldsAndSameId_True() {
        DirectionDto dirDto1 = new DirectionDto("direction1", "00", 15L, "vk.com/id123");
        dirDto1.setId(1L);
        DirectionDto dirDto2 = new DirectionDto("direction2", "0013Y", 12L, "vk.com/id1");
        dirDto2.setId(1L);
        assertThat(dirDto1.equals(dirDto2)).isTrue();
    }
}