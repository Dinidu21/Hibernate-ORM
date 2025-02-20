package com.dinidu.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfigurationInstance;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfigurationInstance == null ?
                new FactoryConfiguration() : factoryConfigurationInstance;
    }
    public Session getSessionFactory() {
        return sessionFactory.openSession();
    }
}
