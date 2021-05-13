package com.nixsolutions.apteka.dao;

import java.util.Properties;

import lombok.SneakyThrows;

public abstract class DaoFactory {

    private DaoFactory instance;

    private DaoFactory() {
        init();
    }

    @SneakyThrows
    private void init() {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("daofactory.properties"));
    }

}
