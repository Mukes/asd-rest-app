package com.asd.framework.database;

import java.sql.Connection;

/**
 * Created by Zamuna on 6/12/2017.
 */
public class DbContext {
    private DbStrategy dbStrategy;

    public DbContext(DbStrategy dbStrategy) {
        this.dbStrategy = dbStrategy;
    }

    public Connection executeStrategy() {
        System.out.println("----Db connection started----");
        return dbStrategy.dbConnect();
    }

    public DbStrategy getDbStrategy() {
        return dbStrategy;
    }

    public void setDbStrategy(DbStrategy dbStrategy) {
        this.dbStrategy = dbStrategy;
    }
}
