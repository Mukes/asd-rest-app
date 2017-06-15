package com.asd.framework.dao;

import com.asd.framework.database.Datasource;
import com.asd.framework.model.User;
import com.asd.framework.service.UserService;

import java.sql.*;
import java.util.List;

public class AbstractDao<T> implements com.asd.framework.dao.IDao<T> {
    private Connection connection;
    PreparedStatement preparedStatement;

    ResultSetMapper<T> resultSetMapper;
    ResultSet resultSet = null;

    public AbstractDao() {
        connection = Datasource.getINSTANCE().getConnection();
        resultSetMapper = new ResultSetMapper<T>();
    }

    /*public AbstractDao(Connection connection) {
        this.connection = connection;

        resultSetMapper = new ResultSetMapper<T>();
    }*/

    public Long insert(String tableName, String columnNames, String values) {
        String sql=insertStatement(tableName, columnNames, values);
        return executeUpdate(sql, null);
    }

    public Long update(String tableName, String statement, Long id) {
        String updateStr = updateStatement(tableName, statement, id);
        return executeUpdate(updateStr, id);
    }

    public Long delete(String tableName, Long id) {
        return executeUpdate(deleteStatement(tableName, id), id);
    }

    private Long executeUpdate(String sql, Long id){
        Integer updateId=0;
        try {
            preparedStatement=connection.prepareStatement(sql);
            updateId= preparedStatement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (updateId<1){
                updateId=(int)(long)id;
            }
            if (id==null){
                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {
                    updateId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (long)updateId;
    }

    public T get(String tableName, String relation, String condition, Class clazz) {
        return executeQuery(tableName, relation, condition, null, clazz).get(0);
    }

    public List<T> getAll(String tableName, String relation, String condition, String pagination, Class clazz) {
        return executeQuery(tableName, relation, condition, pagination, clazz);
    }

    private List<T> executeQuery(String tableName, String relation, String condition, String pagination, Class clazz){
        String query = getQuery(tableName, relation, condition, pagination);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // simple JDBC code to run SQL query and populate resultSet - END
        List<T> list = resultSetMapper.mapRersultSetToObject(resultSet, clazz);
        return list;
    }

   /* public Integer customQuery(String statement) {
        //Handle handle = dbi.open();
        Update update = handle.createStatement(statement);
        Integer id = update.execute();
        System.out.println("Delete Id:"+id);
        //handle.close();
        return id;
    }*/

    private String insertStatement(String tableName, String columnNames, String values){
        String insertStr = "INSERT INTO "+tableName+" ( "+ columnNames+" ) VALUES ( "+values+" )";
        System.out.println("Insert Statement:"+insertStr);
        return insertStr;
    }

    private String updateStatement(String tableName, String statement, Long id){
        String updateStr = "UPDATE "+tableName+" SET "+ statement+" WHERE id =  "+id;
        System.out.println("Update Statement:"+updateStr);
        return updateStr;
    }

    private String deleteStatement(String tableName, Long id){
        return "DELETE FROM "+tableName+" WHERE id =  "+id;
    }

    private String getQuery(String tableName, String relation, String condition, String pagination){
        String query = "SELECT "+relation+" FROM "+ tableName;
        if (condition!=null){
            query+=" WHERE "+condition;
        }
        if (pagination!=null){
            query+=pagination;
        }
        System.out.println("Query:"+query);
        return query;
    }

    public static void main(String[] args) {
        User user = new User("email");

        UserService userService = new UserService(User.class);

        user.setEmail("newe email");
        userService.update(user, 2L, false);
        User user1 = userService.getbyid(2L);
        System.out.println("Single user:"+user1);

    }

}
