package ru.ershov.pro_education.service;

import lombok.Getter;
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
import ru.ershov.pro_education.mapper.AbstractMapper;
import ru.ershov.pro_education.service.clazz.TestAbstractDto;
import ru.ershov.pro_education.service.clazz.TestAbstractEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class AbstractCrudServiceTest {

    @Autowired
    private ModelMapper modelMapper;

    @Mock
    private AbstractDao<TestAbstractEntity, Number> dao;

    private TestAbstractCrud crud = null;

    class TestAbstractCrud extends AbstractCrudService<TestAbstractEntity, TestAbstractDto, Number> {
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
    void findAll_Empty() {
        when(dao.findAll())
                .thenReturn(Collections.emptyList());
        List<TestAbstractDto> all = crud.findAll();
        Assertions.assertTrue(all.isEmpty());
    }

    @Test
    void findAll_OneObject() {
        TestAbstractEntity value = new TestAbstractEntity();
        value.setId(1L);
        ArrayList<TestAbstractEntity> expectedEntity =
                new ArrayList<>() {{ add(value); }};

        when(dao.findAll())
                .thenReturn(expectedEntity);
        List<TestAbstractDto> all = crud.findAll();
        Assertions.assertEquals(1, all.size());
        Assertions.assertEquals(1, all.get(0).getId());
    }

    @Test
    void insert() {
        TestAbstractEntity value = new TestAbstractEntity();
        value.setId(1L);

        when(dao.insert(any(TestAbstractEntity.class)))
                .thenReturn(value);

        TestAbstractDto actualDto = crud.insert(new TestAbstractDto());
        TestAbstractMapper mapper = new TestAbstractMapper();
        TestAbstractDto expectedDto = mapper.toDto(value);
        Assertions.assertEquals(expectedDto, actualDto);
    }

    @Test
    void update() {
        TestAbstractEntity value = new TestAbstractEntity();
        value.setId(1L);
        value.setField("new field");
        when(dao.update(eq(1L), any()))
                .thenReturn(value);

        TestAbstractDto newEntity = new TestAbstractDto();
        newEntity.setField("new field");
        TestAbstractDto update = crud.update(1L, newEntity);
        Assertions.assertEquals(1, update.getId());
        Assertions.assertEquals("new field", update.getField());

    }

    @Test
    void existById() {
        when(dao.existById(1))
                .thenReturn(true);
        Assertions.assertTrue(crud.existById(1));
    }

    @Test
    void delete() {
        when(dao.delete(1))
                .thenReturn(true);

        Assertions.assertTrue(crud.delete(1));
    }
}