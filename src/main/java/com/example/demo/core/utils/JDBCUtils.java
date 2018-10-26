package com.example.demo.core.utils;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    private static PGConnectionPoolDataSource poolDataSource = null;

    public static Connection getAccountsConnection() {
        if (poolDataSource == null) {
            poolDataSource = new PGConnectionPoolDataSource();
            poolDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
            poolDataSource.setUser("postgres");
            poolDataSource.setPassword("1");
        }
        try {
            return poolDataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
