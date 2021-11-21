package ru.ershov.pro_education.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ershov.pro_education.dao.AbstractDao;
import ru.ershov.pro_education.dto.AbstractDto;
import ru.ershov.pro_education.entity.AbstractEntity;
import ru.ershov.pro_education.mapper.AbstractMapper;
import ru.ershov.pro_education.service.clazz.TestAbstractDto;
import ru.ershov.pro_education.service.clazz.TestAbstractEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AbstractCrudTest {

    @Autowired
    private ModelMapper modelMapper;

    @Mock
    private AbstractDao<TestAbstractEntity, Number> dao;

    private TestAbstractCrud crud = null;

    class TestAbstractCrud extends AbstractCrud<TestAbstractEntity, TestAbstractDto, Number> {
        protected TestAbstractCrud(AbstractDao<TestAbstractEntity, Number> dao, TestAbstractMapper mapper) {
            super(dao, mapper, RuntimeException.class);
        }
    }

    @Getter
    @Setter
    class TestAbstractMapper extends AbstractMapper<TestAbstractEntity, TestAbstractDto> {
        protected TestAbstractMapper() {
            super(TestAbstractEntity.class, TestAbstractDto.class);
            setMapper(modelMapper);
        }
    }

    @BeforeEach
    void init() {
        crud = new TestAbstractCrud(dao, new TestAbstractMapper());
    }

    @Test
    void findById_Success() {
        TestAbstractEntity value = new TestAbstractEntity();
        value.setId(1L);
        when(dao.findById(any()))
                .thenReturn(Optional.of(value));
        AbstractDto byId = crud.findById(1);
        Assertions.assertEquals(1L, byId.getId());
    }

    @Test
    void findById_Exception() {
        when(dao.findById(any()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(RuntimeException.class, () -> crud.findById(1));
    }

    @Test
    void findAll() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void existById() {
    }

    @Test
    void delete() {
    }
}