package com.example.demo.core.utils;

import org.postgresql.ds.PGConnectionPoolDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseUtils {
    private static PGConnectionPoolDataSource poolDataSource = null;
    public static final String DB_VARCHAR = "VARCHAR";

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
        throw new DataSourceLookupFailureException("Can't connect to datasource");
    }
}
