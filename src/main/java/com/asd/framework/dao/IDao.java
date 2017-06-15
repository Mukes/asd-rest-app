package com.asd.framework.dao;

import java.util.List;

public interface IDao<T> {
    Long insert(String tableName, String columnNames, String values);

    Long update(String tableName, String statement, Long id);

    Long delete(String tableName, Long id);

    T get(String tableName, String relation, String condition, Class clazz);

    List<T> getAll(String tableName, String relation, String condition, String pagination, Class clazz);
}
