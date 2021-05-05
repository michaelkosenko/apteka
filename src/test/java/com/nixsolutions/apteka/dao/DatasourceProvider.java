package com.nixsolutions.apteka.dao;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import lombok.SneakyThrows;

public class DatasourceProvider implements TestRule {

    private static DatasourceProvider instance = new DatasourceProvider();

    private final DataSource ds;

    @SneakyThrows
    private DatasourceProvider() {

        String file = getClass().getClassLoader().getResource("schema.sql").getFile();

        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl(properties.getProperty("datasource.connection.url") + ";INIT=RUNSCRIPT FROM '" + file + "'");
        ds.setUser(properties.getProperty("datasource.connection.username"));
        ds.setPassword(properties.getProperty("datasource.connection.password"));
        this.ds = ds;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                System.out.println("start");
                base.evaluate();
                System.out.println("stop");
            }
        };
    }

    public static DatasourceProvider instance() {
        return instance;
    }

    public DataSource getDatasource() {
        return ds;
    }

    @SneakyThrows
    public Connection getConnection() {
        return ds.getConnection();
    }
}
