package com.asd.framework.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Zamuna on 6/12/2017.
 */
public class MySqlStrategy implements com.asd.framework.database.DbStrategy {
    private Connection connection;

    private String dburl;
    private String username;
    private String password;

    public MySqlStrategy(String dburl, String username, String password) {
        this.dburl = dburl;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection dbConnect() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(dburl, username, password);
                System.out.println("--" + dburl + "---" + username + "----" + password);
                System.out.println("Connection done");
            } catch (SQLException s) {
                System.out.println("Error in Connection");
                s.printStackTrace();
            }
        }
        return connection;
    }
}
