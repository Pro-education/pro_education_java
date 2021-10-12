package ru.ershov.pro_education.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Id;
import ru.ershov.pro_education.annotation.Table;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractDao<T, ID> implements Dao<T, ID> {

    public static final String SET_TEMPLATE = "set %s = %s";
    public static final String WHERE_ADDER = "WHERE id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Class<T> tableClass;
    private final String selectPattern;
    private final String insertPattern;
    private final String updatePattern;
    private final String deletePattern;

    protected AbstractDao(NamedParameterJdbcTemplate jdbcTemplate, Class<T> tableClass) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableClass = tableClass;
        selectPattern = "SELECT %s FROM %s ";
        insertPattern = "INSERT INTO " + getTableName() + " (%s) VALUES ('%s');";
        updatePattern = "UPDATE " + getTableName() + " %s WHERE %s = %s;";
        deletePattern = "DELETE FROM " + getTableName() + " WHERE %s = %s;";
    }

    public RowMapper<T> getRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            T mappedObject = BeanUtils.instantiateClass(tableClass);
            BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
            bw.setAutoGrowNestedPaths(true);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Map<String, String> columnAndIdDbAndFieldsName = findColumnAndIdDbAndFieldsName(tableClass);
            for (int index = 1; index <= columnCount; index++) {
                String column;
                Object value;
                try {
                    column = columnAndIdDbAndFieldsName.get(JdbcUtils.lookupColumnName(metaData, index));
                    value = JdbcUtils.getResultSetValue(rs, index, Class.forName(metaData.getColumnClassName(index)));

                    bw.setPropertyValue(column, value);
                } catch (TypeMismatchException | NotWritablePropertyException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return mappedObject;
        };
    }

    protected String getBaseSqlQuery() {
        String name = getTableName();
        return String.format(
                selectPattern,
                findColumnAndIdDbName(),
                name);
    }

    public String getTableName() {
        return tableClass.getDeclaredAnnotation(Table.class).name();
    }

    @Override
    public boolean existById(ID id) {
        String sql = getBaseSqlQuery() + WHERE_ADDER;
        MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource("id", id);
        T t = jdbcTemplate.queryForObject(sql, mapSqlParameterSource, getRowMapper());
        return t != null;
    }

    @Override
    public Optional<T> findById(ID id) {
        try {
            String sql = getBaseSqlQuery() + WHERE_ADDER;
            MapSqlParameterSource mapSqlParameterSource =
                    new MapSqlParameterSource("id", id);
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, mapSqlParameterSource, getRowMapper())
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        return jdbcTemplate.query(getBaseSqlQuery(), getRowMapper());
    }

    @Transactional
    public <S extends T> S insert(S entity) {
        String extracted = pasteSqlInsert(entity);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(extracted, new MapSqlParameterSource(), keyHolder, new String[]{"id"});
        try {
            invokeSetter(entity, "id", keyHolder.getKey());
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Transactional
    public <S extends T> S update(ID id, S newEntity) {
        if (!existById(id)) {
            throw new IllegalArgumentException();
        }
        String sql = pasteSqlUpdate(id, newEntity);
        jdbcTemplate.update(sql, new MapSqlParameterSource());
        try {
            invokeSetter(newEntity, "id", id);
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            throw new IllegalArgumentException();
        }
        return newEntity;
    }

    @Transactional
    public void delete(ID id) {
        jdbcTemplate.update(
                String.format(deletePattern, findIdField(tableClass), id),
                new MapSqlParameterSource()
        );
    }

    private <S extends T> String pasteSqlInsert(S entity) {
        List<String> strNames = new ArrayList<>();
        List<String> strValues = new ArrayList<>();
        try {
            for (Map.Entry<String, Object> fieldAnnotationEntry : findColumnFieldsNameAndValue(entity)) {
                strNames.add(fieldAnnotationEntry.getKey());
                strValues.add(fieldAnnotationEntry.getValue().toString());
            }
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            e.printStackTrace();
        }
        return String.format(insertPattern, String.join(", ", strNames), String.join("', '", strValues));
    }

    private <S extends T> String pasteSqlUpdate(ID id, S entity) {
        List<String> str = new ArrayList<>();
        try {
            for (Map.Entry<String, Object> fieldAnnotationEntry : findColumnFieldsNameAndValue(entity)) {
                str.add(String.format(SET_TEMPLATE, fieldAnnotationEntry.getKey(), fieldAnnotationEntry.getValue()));
            }
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            throw new IllegalArgumentException();
        }
        String idFieldName = findIdField(entity.getClass());
        return String.format(updatePattern, String.join(", ", str), idFieldName, id);
    }

    protected <S extends T> Set<Map.Entry<String, Object>> findColumnFieldsNameAndValue(S entity)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> c = entity.getClass();
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class)) {
                    map.put(field.getDeclaredAnnotation(Column.class).name(), invokeGetter(entity, field.getName()));
                }
            }
            c = c.getSuperclass();
        }
        return map.entrySet();
    }

    private Set<String> findColumnAndIdDbName(Class<?> entityClass) {
        Set<String> fields = new HashSet<>();
        Class<?> c = entityClass;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    fields.add(field.getDeclaredAnnotation(Column.class).name());
                }
            }
            c = c.getSuperclass();
        }
        return fields;
    }

    public String findColumnAndIdDbName() {
        return findColumnAndIdDbName(tableClass).stream().collect(
                Collectors.joining(", " + getTableName() + ".", getTableName() + ".", "")
        );
    }

    private Map<String, String> findColumnAndIdDbAndFieldsName(Class<?> entityClass) {
        Map<String, String> fields = new HashMap<>();
        Class<?> c = entityClass;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    fields.put(field.getDeclaredAnnotation(Column.class).name(), field.getName());
                }
            }
            c = c.getSuperclass();
        }
        return fields;
    }


    private String findIdField(Class<?> entityClass) {
        Class<?> c = entityClass;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    return field.getName();
                }
            }
            c = c.getSuperclass();
        }
        throw new IllegalArgumentException("class " + entityClass + "does not have an id annotation");
    }

    private Class<?> findClassIdField(Class<?> clazz) {
        Class<?> c = clazz;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    return field.getClass();
                }
            }
            c = c.getSuperclass();
        }
        return Long.class;
    }

    private <S extends T> void invokeSetter(S obj, String propertyName, Object variableValue)
            throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, obj.getClass());
        Method setter = pd.getWriteMethod();
        setter.invoke(obj, variableValue);
    }

    private <S extends T> String invokeGetter(S obj, String variableName)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
        Method getter = pd.getReadMethod();
        return getter.invoke(obj).toString();
    }

}
