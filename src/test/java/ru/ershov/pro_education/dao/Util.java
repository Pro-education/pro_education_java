package ru.ershov.pro_education.dao;

import ru.ershov.pro_education.service.clazz.TestAbstractEntity;

public class Util {

    public static final long TEST_ID = 100L;
    public static final long ONE_ID = 1l;
    public static final String TEST_FILED = "test_field";

    public static TestAbstractEntity getFullEntity() {
        return new TestAbstractEntity(TEST_FILED);
    }

    public static TestAbstractEntity getEmptyEntity() {
        return new TestAbstractEntity();
    }
}
