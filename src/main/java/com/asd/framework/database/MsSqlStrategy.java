package com.asd.framework.database;

import java.sql.Connection;

/**
 * Created by Zamuna on 6/13/2017.
 */
public class MsSqlStrategy implements com.asd.framework.database.DbStrategy {
    private String dburl;
    private String username;
    private String password;

    public MsSqlStrategy(String dburl, String username, String password) {
        this.dburl = dburl;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection dbConnect() {
        return null;
    }
}
