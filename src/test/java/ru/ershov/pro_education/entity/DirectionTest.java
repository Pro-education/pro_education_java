package ru.ershov.pro_education.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DirectionTest {

    @Test
    void testEquals_DifferentFieldsAndSameId_True() {
        Direction dir1 = new Direction("direction1", "00", 15L, "vk.com/id123");
        dir1.setId(1L);
        Direction dir2 = new Direction("direction2", "0013Y", 12L, "vk.com/id1");
        dir2.setId(1L);
        assertThat(dir1.equals(dir2)).isTrue();
    }
}