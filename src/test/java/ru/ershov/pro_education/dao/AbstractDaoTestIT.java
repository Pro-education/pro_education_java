package ru.ershov.pro_education.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.ershov.pro_education.config.InitDb;
import ru.ershov.pro_education.service.clazz.TestAbstractEntity;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("it")
@ContextConfiguration(initializers = {InitDb.class})
class AbstractDaoTestIT {

    @Autowired
    AbstractDao<TestAbstractEntity, Long> testDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        String query = "CREATE TABLE test " +
                "(" +
                "    id           BIGSERIAL PRIMARY KEY," +
                "    field        text," +
                "    created_time timestamp default (current_timestamp)," +
                "    updated_time timestamp" +
                "); ";
        jdbcTemplate.execute(query);
    }

    @AfterEach
    void delete() {
        String query = "DROP TABLE test";
        jdbcTemplate.execute(query);
    }

    @Test
    void existBy_False() {
        Assertions.assertFalse(testDao.existById(Util.TEST_ID));
    }

    @Test
    void existBy_True() {
        TestAbstractEntity testAbstractEntity = testDao.insert(Util.getFullEntity());
        Assertions.assertTrue(testDao.existById(testAbstractEntity.getId()));
    }

    @Test
    void insert_FullEntity() {
        TestAbstractEntity testAbstractEntity = testDao.insert(Util.getFullEntity());
        Assertions.assertEquals(Util.getFullEntity(), testAbstractEntity);
    }

    @Test
    void insert_EmptyEntity() {
        TestAbstractEntity testAbstractEntity = testDao.insert(Util.getEmptyEntity());
        Assertions.assertEquals(Util.getEmptyEntity(), testAbstractEntity);
    }

    @Test
    void findById_Empty() {
        Optional<TestAbstractEntity> testAbstractEntity = testDao.findById(Util.TEST_ID);
        Assertions.assertFalse(testAbstractEntity.isPresent());
    }

    @Test
    void findById_Ok() {
        long id = testDao.insert(Util.getFullEntity()).getId();
        Optional<TestAbstractEntity> testAbstractEntity = testDao.findById(id);
        Assertions.assertTrue(testAbstractEntity.isPresent());
        Assertions.assertEquals(Util.getFullEntity(), testAbstractEntity.get());
    }

}
