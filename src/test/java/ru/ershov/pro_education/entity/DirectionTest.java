package ru.ershov.pro_education.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class DirectionTest {

    @Test
    void testEquals_DifferentFieldsAndSameId_True() {
        Direction dir1 = new Direction("direction1", "00", "vk.com/id123", 2l);
        dir1.setId(1L);
        Direction dir2 = new Direction("direction2", "0013Y", "vk.com/id1", 2l);
        dir2.setId(1L);
        assertThat(dir1.equals(dir2)).isTrue();
    }
}