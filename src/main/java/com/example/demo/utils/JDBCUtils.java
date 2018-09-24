package com.example.demo.utils;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    private static PGConnectionPoolDataSource poolDataSource = null;

    public static Connection getAccountsConnection() throws SQLException {
        if (poolDataSource == null) {
            poolDataSource = new PGConnectionPoolDataSource();
            poolDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
            poolDataSource.setUser("postgres");
            poolDataSource.setPassword("1");
        }
        return poolDataSource.getConnection();
    }
}
