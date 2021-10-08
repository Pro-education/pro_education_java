package ru.ershov.pro_education.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.ershov.pro_education.annotation.Column;
import ru.ershov.pro_education.annotation.Id;
import ru.ershov.pro_education.annotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractDao<T, ID> implements Dao<T, ID> {

    protected abstract RowMapper<T> getRowMapper();

    protected abstract NamedParameterJdbcTemplate getJdbcTemplate();

    protected abstract Class<T> getTableClass();

    private String getBaseSqlQuery() {
        String name = getTableName();
        return String.format("select * from %s ", name);
    }

    private String getTableName() {
        return getTableClass().getDeclaredAnnotation(Table.class).name();
    }

    private Map<Field, Column> findFields() {
        Map<Field, Column> map = new HashMap<>();
        Class<?> c = getTableClass();
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class)) {
                    map.put(field, field.getDeclaredAnnotation(Column.class));
                }
            }
            c = c.getSuperclass();
        }
        return map;
    }

    @Override
    public Optional<T> findById(ID id) {
        try {
            String sql = getBaseSqlQuery() + "where id = :id";
            MapSqlParameterSource mapSqlParameterSource =
                    new MapSqlParameterSource("id", id);
            return Optional.ofNullable(
                    getJdbcTemplate().queryForObject(sql, mapSqlParameterSource, getRowMapper())
            );
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        return getJdbcTemplate().query(getBaseSqlQuery(), getRowMapper());
    }

    @Override
    public <S extends T> S save(S entity) {
        Map<Field, Column> fields = findFields();
        String sql = "insert into " + getTableName() + " (%s) values ('%s')";
        String extracted = extracted(entity, fields, sql);
        System.out.println(extracted);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(extracted, new MapSqlParameterSource(), keyHolder, new String[] { "id" });
        try {
            entity.getClass()
                    .getMethod("setId", Long.class)
                    .invoke(entity, keyHolder.getKey().longValue());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private <S extends T> String extracted(S entity, Map<Field, Column> fields, String sqlTemplate) {
        List<String> strNames = new ArrayList<>();
        List<String> strValues = new ArrayList<>();
        for (Map.Entry<Field, Column> fieldAnnotationEntry : fields.entrySet()) {

            try {
                strNames.add(fieldAnnotationEntry.getValue().name());
                String name = fieldAnnotationEntry.getKey().getName();
                String name1 = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
                strValues.add(entity.getClass().getDeclaredMethod(name1).invoke(entity).toString());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return String.format(sqlTemplate, String.join(", ", strNames), String.join("', '", strValues));
    }


}
