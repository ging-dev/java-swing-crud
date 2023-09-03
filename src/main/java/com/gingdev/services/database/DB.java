package com.gingdev.services.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DB {
    private static Connection conn;

    public static Connection getConn() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:sqlite:"+ DB.class.getResource("/data.db"));
        }

        return conn;
    }
}
