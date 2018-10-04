package com.example.demo.utils;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection getAccountsConnection() throws SQLException, URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        PGConnectionPoolDataSource poolDataSource = new PGConnectionPoolDataSource();

        if (dbUri.getUserInfo() != null) {
            poolDataSource.setUser(dbUri.getUserInfo().split(":")[0]);
            poolDataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
        }
        poolDataSource.setUrl(dbUrl);
        return poolDataSource.getConnection();
    }
}
