package com.asd.framework.dao;

import java.util.List;

/**
 * Created by Crawlers on 6/19/2017.
 */
public class MySqlImpl<T> extends DaoImpl<T> {

    public MySqlImpl(AbstractDao<T> dao) {
        super(dao);
    }

    @Override
    Long insertData(String tableName, String columnNames, String values) {
        return dao.insert(tableName, columnNames, values);
    }

    @Override
    Long updateData(String tableName, String statement, Long id) {
        return dao.update(tableName, statement, id);
    }

    @Override
    Long deleteData(String tableName, Long id) {
        return dao.delete(tableName, id);
    }

    @Override
    T getData(String tableName, String relation, String condition, Class clazz) {
        return dao.get(tableName, relation, condition, clazz);
    }

    @Override
    List<T> getAllData(String tableName, String relation, String condition, String pagination, Class clazz) {
        return dao.getAll(tableName, relation, condition, pagination, clazz);
    }
}
