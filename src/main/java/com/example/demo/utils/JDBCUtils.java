package com.example.demo.utils;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    private static PGConnectionPoolDataSource poolDataSource = null;

    public static Connection getAccountsConnection() throws SQLException {
        if (poolDataSource == null) {
            poolDataSource = new PGConnectionPoolDataSource();
            poolDataSource.setUrl("postgres://xwfvhucamvvpzw:fe71fe6cb56913f92736a5310aa2e43e1d40c598f027abb81ffecac8ed899fa6@ec2-54-225-68-133.compute-1.amazonaws.com:5432/de7e9t23pbe9gs");
            poolDataSource.setUser("xwfvhucamvvpzw");
            poolDataSource.setPassword("fe71fe6cb56913f92736a5310aa2e43e1d40c598f027abb81ffecac8ed899fa6");
        }
        return poolDataSource.getConnection();
    }
}
