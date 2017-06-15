package com.asd.framework.database;

import java.sql.Connection;

/**
 * Created by Zamuna on 6/12/2017.
 */
public interface DbStrategy {
    public Connection dbConnect();
}
