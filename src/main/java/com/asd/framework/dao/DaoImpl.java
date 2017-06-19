package com.asd.framework.dao;

import java.util.List;

public abstract class DaoImpl<T> {
    protected IDao<T> dao;

    public DaoImpl(IDao<T> dao) {
        this.dao = dao;
    }

    abstract Long insertData(String tableName, String columnNames, String values);

    abstract Long updateData(String tableName, String statement, Long id);

    abstract Long deleteData(String tableName, Long id);

    abstract T getData(String tableName, String relation, String condition, Class clazz);

    abstract List<T> getAllData(String tableName, String relation, String condition, String pagination, Class clazz);
}
