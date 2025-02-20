package com.dinidu.config;

import com.dinidu.entity.Customer;
import com.dinidu.entity.IDCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfigurationInstance;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration cfg = new Configuration().configure();

        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(IDCard.class);

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
