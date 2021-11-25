package ru.ershov.pro_education.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DirectionTest {

    @Test
    void testEquals(){
        Direction firstDir = new Direction();
        firstDir.setId(1L);
        firstDir.setName("value");
        firstDir.setNumber("value");
        firstDir.setReviewsId(2L);
        firstDir.setVkLink("value");
        Direction secondDir = new Direction();
        secondDir.setId(1L);
        secondDir.setName("another value");
        secondDir.setNumber("another value");
        secondDir.setReviewsId(3L);
        secondDir.setVkLink("another value");
        Assertions.assertTrue(firstDir.equals(secondDir));
    }

    @Test
    void TestNoEquals(){
        Direction firstDir = new Direction();
        firstDir.setId(1L);
        firstDir.setName("value");
        firstDir.setNumber("value");
        firstDir.setReviewsId(3L);
        firstDir.setVkLink("value");
        Direction secondDir = new Direction();
        secondDir.setId(2L);
        secondDir.setName("value");
        secondDir.setNumber("value");
        secondDir.setReviewsId(3L);
        secondDir.setVkLink("value");
        Assertions.assertFalse(firstDir.equals(secondDir));

    }
}