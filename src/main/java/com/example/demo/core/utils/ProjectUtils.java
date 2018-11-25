package com.example.demo.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.core.ConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.text.MessageFormat;

public class ProjectUtils {

    public static Logger getLogger(Class clazz) {
        return LogManager.getLogger(clazz);
    }

    public static String getLocationException(Exception exception) {
        String TYPE = exception.toString();
        StackTraceElement ELEMENT = exception.getStackTrace()[0];


        return MessageFormat.format("\nType: {0}\nMethod: {1}.{2}().\nLine: {3}",
                TYPE,
                ELEMENT.getClassName(),
                ELEMENT.getMethodName(),
                ELEMENT.getLineNumber());
    }

    private NamedParameterJdbcTemplate createNamedParameterJdbcTemplate(DataSource dataSource) {
        JdbcTemplate result = new JdbcTemplate(dataSource);
        result.setFetchSize(100);

        return new NamedParameterJdbcTemplate(result);
    }


}
