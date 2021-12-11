package ru.ershov.pro_education.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
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
import ru.ershov.pro_education.annotation.ManyToOne;
import ru.ershov.pro_education.annotation.Table;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractDao<T, ID extends Number> implements Dao<T, ID> {

    public static final String SET_TEMPLATE = "SET %s = %s";
    public static final String WHERE_ADDER = "WHERE id = :id";

    protected final NamedParameterJdbcTemplate jdbcTemplate;
    private final Class<T> tableClass;
    private final String selectPattern;
    private final String existPattern;
    private final String insertPattern;
    private final String updatePattern;
    private final String deletePattern;
    private final String selectAllFromParent;

    protected AbstractDao(NamedParameterJdbcTemplate jdbcTemplate, Class<T> tableClass) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableClass = tableClass;
        selectPattern = "SELECT %s FROM " + getTableName() + " ";
        existPattern = "SELECT count(*) FROM " + getTableName() + " ";
        insertPattern = "INSERT INTO " + getTableName() + " (%s) VALUES ('%s');";
        updatePattern = "UPDATE " + getTableName() + " %s WHERE %s = %s;";
        deletePattern = "DELETE FROM " + getTableName() + " WHERE %s = %s;";
        selectAllFromParent = getBaseSqlQuery() + " WHERE %s = :parentId;";
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
        return String.format(selectPattern, findColumnAndIdDbName());
    }

    public String getTableName() {
        return tableClass.getDeclaredAnnotation(Table.class).name();
    }

    @Override
    public boolean existById(ID id) {
        String sql = existPattern + WHERE_ADDER;
        MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource("id", id);
        Integer t = jdbcTemplate.queryForObject(sql, mapSqlParameterSource, Integer.class);
        return t > 0;
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
        invokeSetter(entity, "id", keyHolder.getKey());
        return entity;
    }

    @Transactional
    public <S extends T> S update(ID id, S newEntity) {
        if (!existById(id)) {
            throw new IllegalArgumentException();
        }
        String sql = pasteSqlUpdate(id, newEntity);
        jdbcTemplate.update(sql, new MapSqlParameterSource());
        invokeSetter(newEntity, "id", id);
        return newEntity;
    }

    @Transactional
    public boolean delete(ID id) {
        jdbcTemplate.update(
                String.format(deletePattern, findIdField(tableClass), id),
                new MapSqlParameterSource()
        );
        return true;
    }

    protected List<T> getAllFromParent(ID parentId, Class<?> clazz) {
        MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource("parentId", parentId);
        return jdbcTemplate.query(String.format(selectAllFromParent, findColumnManyToOne(clazz)), mapSqlParameterSource, getRowMapper());
    }

    private <S extends T> String pasteSqlInsert(S entity) {
        List<String> strNames = new ArrayList<>();
        List<String> strValues = new ArrayList<>();
        for (Map.Entry<String, String> fieldAnnotationEntry : findColumnFieldsNameAndValue(entity)) {
            strNames.add(fieldAnnotationEntry.getKey());
            strValues.add(fieldAnnotationEntry.getValue());
        }
        return String.format(insertPattern, String.join(", ", strNames), String.join("', '", strValues));
    }

    private <S extends T> String pasteSqlUpdate(ID id, S entity) {
        List<String> str = new ArrayList<>();
        for (Map.Entry<String, String> fieldAnnotationEntry : findColumnFieldsNameAndValue(entity)) {
            str.add(String.format(SET_TEMPLATE, fieldAnnotationEntry.getKey(), fieldAnnotationEntry.getValue()));
        }
        String idFieldName = findIdField(entity.getClass());
        return String.format(updatePattern, String.join(", ", str), idFieldName, id);
    }

    protected String findColumnManyToOne(Class<?> clazz) {
        for (Field field : tableClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ManyToOne.class)
                    && field.isAnnotationPresent(Column.class)
                    && field.getDeclaredAnnotation(ManyToOne.class).clazz().equals(clazz)
            ) {
                return field.getDeclaredAnnotation(Column.class).name();
            }
        }
        throw new IllegalArgumentException();
    }

    protected <S extends T> Set<Map.Entry<String, String>> findColumnFieldsNameAndValue(S entity) {
        Map<String, String> map = new HashMap<>();
        Class<?> c = entity.getClass();
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)
                        && !field.getDeclaredAnnotation(Column.class).onlyRead()
                        && !field.isAnnotationPresent(Id.class)
                ) {
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
        return findColumnAndIdDbName(tableClass)
                .stream()
                .collect(
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
                    return field.getType();
                }
            }
            c = c.getSuperclass();
        }
        return Long.class;
    }

    private <S extends T> void invokeSetter(S obj, String propertyName, Object variableValue) {
        try {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            final String setterName = "set" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
            Method setterMethod = tableClass.getMethod(setterName, findClassIdField(obj.getClass()));
            MethodHandle methodHandle = lookup.unreflect(setterMethod);
            final CallSite site = LambdaMetafactory.metafactory(lookup,
                    "accept",
                    MethodType.methodType(BiConsumer.class),
                    MethodType.methodType(void.class, Object.class, Object.class),
                    methodHandle,
                    methodHandle.type());
            BiConsumer<T, Object> setterConsumer = (BiConsumer<T, Object>) site.getTarget().invokeExact();
            setterConsumer.accept(obj, variableValue);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private String invokeGetter(T obj, String variableName) {
        Function<T, Object> getterFunction = (o) -> "no get value";
        try {
            final String getterName = "get" + Character.toUpperCase(variableName.charAt(0)) + variableName.substring(1);
            final Method method = tableClass.getMethod(getterName);
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            final MethodHandle methodHandle = lookup.unreflect(method);
            final CallSite site = LambdaMetafactory.metafactory(lookup,
                    "apply",
                    MethodType.methodType(Function.class),
                    MethodType.methodType(Object.class, Object.class),
                    methodHandle,
                    methodHandle.type());
            getterFunction = (Function<T, Object>) site.getTarget().invokeExact();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        Object apply = getterFunction.apply(obj);
        if (apply == null)
            return null;
        return apply.toString();
    }

}
