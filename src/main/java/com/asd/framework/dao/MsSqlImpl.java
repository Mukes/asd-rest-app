package com.asd.framework.dao;

import java.util.List;

public class MsSqlImpl<T> extends DaoImpl<T>{
    public MsSqlImpl(AbstractDao<T> dao) {
        super(dao);
    }

    @Override
    Long insertData(String tableName, String columnNames, String values) {
        return null;
    }

    @Override
    Long updateData(String tableName, String statement, Long id) {
        return null;
    }

    @Override
    Long deleteData(String tableName, Long id) {
        return null;
    }

    @Override
    T getData(String tableName, String relation, String condition, Class clazz) {
        return null;
    }

    @Override
    List<T> getAllData(String tableName, String relation, String condition, String pagination, Class clazz) {
        return null;
    }
}
