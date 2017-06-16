package com.asd.framework.service;

import com.asd.framework.dao.AbstractDao;
import com.asd.framework.error.ErrorMessage;
import com.asd.framework.model.AbstractMetaData;
import com.asd.framework.validation.FacadeValidator;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class AbstractService<T> extends FacadeValidator<T> implements com.asd.framework.service.IService<T> {
    private StringBuilder statement;
    private StringBuilder columns;
    private StringBuilder values;
    private Map<String, String> columnToValueMap;
    private Map<String, Object> valueMap;
    private AbstractDao<T> dao;

    private final Class<T> clazz;

    public AbstractService(Class<T> clazz) {
        this.clazz = clazz;
        template();
    }

    private final void template() {
        //Algorithm Steps
        initDao();
        initStringBuilder();
    }

    private void initDao() {
        this.dao = new AbstractDao<>();
    }

    private void initStringBuilder() {
        columns = new StringBuilder();
        values = new StringBuilder();
        statement = new StringBuilder();
    }

    @Override
    public Long insert(T t) {
        //Send response from here if not valid
        Map<Boolean, List<ErrorMessage>> map = validate(t);
        if (getFirstKey(map)) {
            if (t instanceof AbstractMetaData) {
                ((AbstractMetaData) t).setCreatedAt(getCurrentDateTime());
            }
            columnToValueMap = classToDbFieldMap(t.getClass());
            valueMap = mapProperties(t);
            if (valueMap != null) {
                columnOrValues(columnToValueMap, valueMap);
                Long id = dao.insert(getTableName(t), columns.toString(), values.toString());
                //Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, id.toString(), HttpStatus.getMessage(HttpStatus.OK_200))).build();
                return id;
            }
        }
        return null;
    }

    @Override
    public Integer update(T t, Long id, boolean validate) {
        boolean isValid = true;
        if (validate) {
            Map<Boolean, List<ErrorMessage>> map = validate(t);
            isValid = getFirstKey(map);
        }

        if (isValid) {
            if (t instanceof AbstractMetaData) {
                ((AbstractMetaData) t).setUpdatedAt(getCurrentDateTime());
            }
            Long row = dao.update(getTableName(t), updateStatement(t), id);
            return (int) (long) row;
        } else {
            return null;
        }
    }

    public Integer updateCustom(Long id, String statement, Class clazz) {
        Long row = dao.update(getTableName(clazz), statement, id);
        return (int) (long) row;
    }

    @Override
    public void addList(List<T> l) {

    }

    @Override
    public List<T> getAll(String searchText, List<String> searchFields, String offset, String limit) {
        return getAll(searchText, searchFields, offset, limit, clazz, false);
    }

    public List<T> getAll(String searchText, List<String> searchFields, String offset, String limit, Class clazz, boolean isOverridden) {
        String condition = null;
        if (isOverridden) {
            condition = getCondition(searchText, searchFields);
        }
        List<T> t = dao.getAll(getTableName(clazz), getRelation(classToDbFieldMap(clazz)), condition, getPagination(offset, limit), clazz);
        System.out.println("Got T:" + t.toString());
        if (t == null) {
            t = new ArrayList<>();
        }
        return t;
    }

    @Override
    public List<T> customGetAll(Map<String, String> conditionMap) {
        List<T> t = dao.getAll(getTableName(clazz), getRelation(classToDbFieldMap(clazz)), getCustomCondition(conditionMap), null, clazz);
        return t;
    }

    @Override
    public T getbyid(Long id) {
        System.out.println("inside get by id");
        T t = dao.get(getTableName(clazz), getRelation(classToDbFieldMap(clazz)), " id = " + id, clazz);
        return t;
    }

    @Override
    public Long delete(Long id) {
        return dao.delete(getTableName(clazz), id);
    }

    public String getTableName(Class t) {
        return (t.getSimpleName());
        //return snakeCase(t.getSimpleName());
    }

    private String getTableName(T t) {
        return (t.getClass().getSimpleName());
        //return snakeCase(t.getClass().getSimpleName());
    }

    /*private String snakeCase(String str){
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }*/

    private Map<String, String> classToDbFieldMap(Class clazz) {
        Map<String, String> map = new HashMap<>();
   /* for (Field field : clazz.getDeclaredFields()) {
      map.put(field.getName(), snakeCase(field.getName()));
    }*/
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.getName().equalsIgnoreCase("serialVersionUID")) {
                    //map.put(field.getName(), snakeCase(field.getName()));
                    map.put(field.getName(), (field.getName()));
                }
            }
            clazz = clazz.getSuperclass();
        }
        return map;
    }

    public Map<String, Object> mapProperties(T bean) {
        Map<String, Object> properties = new HashMap<>();
        try {
            for (PropertyDescriptor property : Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors()) {
                String name = property.getName();
                Object value = property.getReadMethod().invoke(bean);
                if (!name.equalsIgnoreCase("id")) {
                    if (value instanceof String || value instanceof Enum) {
                        value = "'" + value + "'";
                    }
                    properties.put(name, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties;
    }

    private void columnOrValues(Map<String, String> map, Map<String, Object> valueMap) {
        columns.setLength(0);
        values.setLength(0);
        //Change with Lamda expression later
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            values.append(valueMap.get(pair.getKey())).append(" , ");
            columns.append(pair.getValue()).append(" , ");
            it.remove(); // avoids a ConcurrentModificationException
        }
        values.delete(values.lastIndexOf(" , "), values.lastIndexOf(" , ") + 3);
        columns.delete(columns.lastIndexOf(" , "), columns.lastIndexOf(" , ") + 3);
        //return isColumn ? columns.toString() : values.toString();
    }

    private String updateStatement(T t) {
        columnToValueMap = classToDbFieldMap(t.getClass());
        valueMap = mapProperties(t);
        statement.setLength(0);
        Iterator it = valueMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getValue() != null && columnToValueMap.get(pair.getKey()) != null) {
                statement.append(columnToValueMap.get(pair.getKey())).append(" = ").append(pair.getValue()).append(" , ");
            }
            it.remove();
        }
        System.out.println("Statement:" + statement.toString());
        statement.delete(statement.lastIndexOf(" , "), statement.lastIndexOf(" , ") + 3);
        return statement.toString();
    }

    private String getRelation(Map<String, String> columnToValueMap) {
        columns.setLength(0);
        Iterator it = columnToValueMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            columns.append(pair.getValue()).append(" AS ");
            columns.append(pair.getKey()).append(" , ");
            it.remove();
        }
        columns.delete(columns.lastIndexOf(" , "), columns.lastIndexOf(" , ") + 3);
        return columns.toString();
    }

    private String getCondition(String searchText, List<String> searchFields) {
        if (searchText != null && searchFields.size() > 0) {
            StringBuilder query = new StringBuilder();
            query.append("(");
            for (String str : searchFields) {
                query.append(" LOWER(").append(str).append(") like LOWER('").append(searchText).append("%')").append(" or ");
            }
            query.delete(query.lastIndexOf(" or "), query.lastIndexOf(" or ") + 3);
            query.append(")");
            return query.toString();
        }
        return null;
    }

    private String getPagination(String offset, String limit) {
        StringBuilder pagination = new StringBuilder();
        if (limit != null) {
            pagination.append(" LIMIT ").append(limit);
            if (offset != null) {
                pagination.append(" OFFSET ").append(offset);
            }
        }
        return pagination.toString();
    }

    private String getCurrentDateTime() {
        Date date = new Date();
        DateFormat readFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStr = null;
        try {
            dateStr = readFormat.parse(date.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writeFormat.format(dateStr);
    }

    private String getCustomCondition(Map<String, String> customMap) {
        final StringBuilder condition = new StringBuilder();
        customMap.forEach((k, v) -> condition.append(k + "='" + v + "' AND "));
        System.out.println("custom condition:"+condition.toString());
        condition.delete(condition.lastIndexOf(" AND "), condition.lastIndexOf(" AND ") + 3);
        return condition.toString();
    }

    private Boolean getFirstKey(Map<Boolean, List<ErrorMessage>> map) {
        return map.keySet().iterator().next();
    }
}
