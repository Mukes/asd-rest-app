package com.asd.framework.database;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Datasource {
    private static Datasource INSTANCE = new Datasource();
    private Properties properties = new Properties();
    private InputStream input = null;

    private com.asd.framework.database.DbContext dbContext;

    private Datasource() {
        initDbContext();
    }

    public void initDbContext() {
        try {
            System.out.println("loaded:"+getClass().getClassLoader().toString());
            input = getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(input);
            String url = properties.getProperty("dburl");
            String username = properties.getProperty("dbusername");
            String password = properties.getProperty("password");
            if ("mysql".equalsIgnoreCase(properties.getProperty("db"))) {
                Class.forName("com.mysql.jdbc.Driver");
                dbContext = new com.asd.framework.database.DbContext(new MySqlStrategy(url, username, password));
            } else if ("mssql".equalsIgnoreCase(properties.getProperty("db"))) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                dbContext = new com.asd.framework.database.DbContext(new MsSqlStrategy(url, username, password));
            }
            //dbContext.executeStrategy();
            System.out.println(properties.getProperty("dbusername"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection() {
        return dbContext.executeStrategy();
    }

    public static Datasource getINSTANCE() {
        return INSTANCE;
    }

}

